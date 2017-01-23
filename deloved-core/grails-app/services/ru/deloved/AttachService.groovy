package ru.deloved

import grails.transaction.Transactional
import groovyx.net.http.ContentType
import liquibase.util.SystemUtils
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.codehaus.groovy.grails.web.servlet.mvc.exceptions.ControllerExecutionException
import org.imgscalr.Scalr
import org.springframework.web.multipart.MultipartFile

import javax.imageio.ImageIO
import javax.servlet.http.HttpServletResponse
import java.awt.image.BufferedImage

@Transactional
class AttachService {

	def grailsApplication


	def attachExists(Attachment attach) {
		return new File(attach).exists()
	}

	def getAttachPath(String fileCategoryDir, Profile owner) {
		String storageDir = '.' + File.separatorChar + 'delovedUpload'
		return storageDir + File.separatorChar + (owner?.id ?: '0') + File.separatorChar + fileCategoryDir
	}

	def resizeImage(MultipartFile file, File newFileThumb, Attachment thumb, Profile owner, int targetSize) {
		InputStream is = file.getInputStream()
		BufferedImage thumbPic = ImageIO.read(is)
		if (thumbPic.getWidth() > targetSize || thumbPic.getHeight() > targetSize) {
			thumbPic = Scalr.resize(thumbPic, Scalr.Method.SPEED, Scalr.Mode.AUTOMATIC, targetSize)
		}
		is.close()
		ImageIO.write(thumbPic, file.contentType.replaceAll("image/", ""), newFileThumb)

		if (thumb == null) {
			thumb = new Attachment()
		}
		thumb.name = file.originalFilename
		thumb.file = newFileThumb.name
		thumb.size = newFileThumb.size()
		thumb.owner = owner
		thumb.mimeType = file.contentType

		return thumb
	}





	def uploadImageAndThumbnail(MultipartFile file,
								String storageDirectory,
								Profile owner,
								Attachment image,
								Attachment thumb,
								Closure closure) {
		//TODO над по-грамотнее написать
		if (!['image/png', 'image/gif', 'image/jpeg'].contains(file.getContentType())) {
			return false
		}


		String newFilenameBase = UUID.randomUUID().toString()



		String originalFileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))

		String newFilename = newFilenameBase + originalFileExtension
		String newFilenameThumb = newFilenameBase + "th" + originalFileExtension

		File newFile = new File(getAttachPath(storageDirectory, owner) + File.separatorChar + newFilename)
		newFile.mkdirs()
		File newFileThumb = new File(getAttachPath(storageDirectory, owner) + File.separatorChar + newFilenameThumb)

		def oldFiles = []
		if (thumb) {
			oldFiles.add(thumb.file)
		}
		if (image) {
			oldFiles.add(image.file)
		}

		log.debug("Create thumbnail file:" + newFileThumb.absolutePath)
		thumb = resizeImage(file, newFileThumb, thumb, owner, 200)

		log.debug("Create main file:" + newFile.absolutePath)
		image = resizeImage(file, newFile, image, owner, 1000)

		if (closure && closure.call(thumb, image)) {
			oldFiles.each { fname ->
				File picFile = new File("$storageDirectory/$fname")
				log.debug("delete file:" + picFile.absolutePath)
				picFile.delete()
			}
			return true
		}
		return false
	}

	def cropThumbnail(GrailsParameterMap params,
					  String storageDirectory,
					  Attachment attImage,
					  Attachment attThumb) {

		int x = Double.valueOf(params.x).intValue()
		int y = Double.valueOf(params.y).intValue()
		int w = Double.valueOf(params.w).intValue()
		int h = Double.valueOf(params.h).intValue()
		log.debug("x:" + x)
		log.debug("y:" + y)
		log.debug("w:" + w)
		log.debug("h:" + h)
		if (w > 0 && h > 0 && x >= 0 && y >= 0) {
			if (attImage) {
				String newFilenameBase = UUID.randomUUID().toString()
				String originalFileExtension = attThumb.name.substring(attThumb.name.lastIndexOf("."))
				String newFilenameThumb = newFilenameBase + "th" + originalFileExtension

				def oldFiles = [attThumb.file]


				File newFileThumb = new File(getAttachPath(storageDirectory, attThumb.owner) + File.separatorChar + newFilenameThumb)
				File picFile = new File(getAttachPath(storageDirectory, attImage.owner) + File.separatorChar + attImage.file)


				// File picFile = new File("$storageDirectory/${attImage.file}")
				//File newFileThumb = new File("$storageDirectory/$newFilenameThumb")

				BufferedImage thumbPic = Scalr.crop(ImageIO.read(picFile), x, y, w, h)
				thumbPic = Scalr.resize(thumbPic, Scalr.Method.SPEED, Scalr.Mode.AUTOMATIC, 200)
				ImageIO.write(thumbPic, attImage.mimeType.replaceAll("image/", ""), newFileThumb)

				attThumb.file = newFileThumb.name
				attThumb.size = newFileThumb.size()
				attThumb.save(flush: true);

				oldFiles.each { fname ->
					//File pFile = new File("$storageDirectory/${fname}")

					File pFile = new File(getAttachPath(storageDirectory, attThumb.owner) + File.separatorChar + fname)
					log.debug("delete file:" + pFile.absolutePath)
					pFile.delete()
				}

				return true
			}
		}
		return false
	}


	def deleteImageAndThumbnail(String storageDirectory,
								Attachment attImage,
								Attachment attThumb,
								Closure closure) {
		def oldFiles = []
		if (attImage) {
			oldFiles.add(attImage.file)
		}
		if (attThumb) {
			oldFiles.add(attThumb.file)
		}
		if (closure && closure.call()) {
			if (attImage) {
				attImage.delete(flush: true)
			}
			if (attThumb) {
				attThumb.delete(flush: true)
			}
			oldFiles.each { fname ->
				File pFile = new File(getAttachPath(storageDirectory, attImage.owner) + File.separatorChar + fname)
				//File pFile = new File("$storageDirectory/${fname}")
				log.debug("delete file:" + pFile.absolutePath)
				pFile.delete()
			}
		}
	}

	def sendFile(String storageDirectory, Attachment att, HttpServletResponse response, boolean asAttachment = false) {


		//File file = new File("$storageDirectory/${att.file}")
		File file = new File(getAttachPath(storageDirectory, att.owner) + File.separatorChar + att.file)

		if(!file.exists()) {
			return null
		}


		response.setContentType(att.mimeType)
		response.setContentLength(att.size);
		if (asAttachment) {
			response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(att.name, 'UTF-8').replaceAll("\\+", "%20"));
		}
		InputStream input = null;
		def os = null
		try {
			os = response.getOutputStream()
			input = FileUtils.openInputStream(file)
			IOUtils.copy(input, os)
			os.flush()
		} catch (IOException e) {
			throw new ControllerExecutionException("I/O error copying file to response: " + e.getMessage(), e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
			if (os != null) {
				try {
					os.close()
				} catch (IOException e) {
				}
			}
		}
	}




	def uploadAttach(MultipartFile file,
					 String storageDirectory,
					 Profile owner,
					 Closure closure) {

		def g = new ApplicationTagLib()

		String newFilenameBase = UUID.randomUUID().toString()
		String originalFileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
		String newFilename = newFilenameBase + originalFileExtension

		File newFile = new File(getAttachPath(storageDirectory, owner) + File.separatorChar + newFilename)
		newFile.mkdirs()


		//File newFile = new File("$storageDirectory/$newFilename")

		file.transferTo(newFile)

		Attachment att = new Attachment(
				name: file.originalFilename,
				file: newFilename,
				size: file.size,
//				owner: owner,
				mimeType: file.contentType
		)
		att.owner = owner
		def thumbnailUrl
		switch (att.mimeType) {
			case 'image/jpeg':
			case 'image/png':
			case 'image/gif':
				thumbnailUrl = null
				break;
			case 'application/vnd.ms-excel':
				thumbnailUrl = g.resource([dir: 'assets/fileupload', file: 'ms-excel.png'])
				break;
			case 'application/pdf':
				thumbnailUrl = g.resource([dir: 'assets/fileupload', file: 'pdf.png'])
				break;
			case 'application/msword':
				thumbnailUrl = g.resource([dir: 'assets/fileupload', file: 'ms-word.png'])
				break;
			case 'application/vnd.ms-powerpoint':
				thumbnailUrl = g.resource([dir: 'assets/fileupload', file: 'ms-powerpoint.png'])
				break;
			default:
				thumbnailUrl = g.resource([dir: 'assets/fileupload', file: 'file.png'])
		}

		if (closure) {
			closure(att, thumbnailUrl)
		}
		return att
	}

	def deleteAttach(String storageDirectory,
					 Attachment att,
					 Closure closure) {
		if (att) {
			def oldFile = att.file
			if (closure && closure.call()) {
				att.delete(flush: true)
				//File pFile = new File("$storageDirectory/$oldFile")
				File pFile = new File(getAttachPath(storageDirectory, att.owner) + File.separatorChar + att.file)
				log.debug("delete file:" + pFile.absolutePath)
				pFile.delete()
			}
		}
	}

	def createAndSendThumb(String storageDirectory,
						   Attachment att,
						   HttpServletResponse response) {
		if (att) {
			switch (att.mimeType) {
				case 'image/jpeg':
				case 'image/png':
				case 'image/gif':
					File picFile = new File(getAttachPath(storageDirectory, att.owner) + File.separatorChar + att.file)
					if(!picFile.exists()) {
						break;
					}
					//File picFile = new File("$storageDirectory/${att.file}")
					BufferedImage thumbnail = Scalr.resize(ImageIO.read(picFile), Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH, 64);
					response.setContentType(att.mimeType)
					def responseOutputStream = response.getOutputStream()
					ImageIO.write(thumbnail, att.mimeType.replaceAll("image/", ""), responseOutputStream)
					responseOutputStream.flush()
					responseOutputStream.close()
					break;
			}
		}
	}

}
