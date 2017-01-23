package ru.deloved

class Attachment {
	String name
	String file
	String mimeType
	int size
	Date dateCreated
	Profile owner


	static constraints = {
		owner nullable: true
	}

	def isImage() {
		switch (mimeType) {
			case 'image/jpeg':
			case 'image/png':
			case 'image/gif':
				return true;
		}
		return false;
	}

	def getIcon() {
		switch (mimeType) {
			case 'application/vnd.ms-excel':
				return 'ms-excel.png'
			case 'application/pdf':
				return 'pdf.png'
			case 'application/msword':
				return 'ms-word.png'
			case 'application/vnd.ms-powerpoint':
				return 'ms-powerpoint.png'
			default:
				return 'file.png'
		}
		return null;
	}

	def getIcon(String size) { // 'sm', 'md', 'lg'
		if (!['sm', 'md', 'lg'].contains(size)) {
			return getIcon()
		}
		switch (mimeType) {
			case 'application/vnd.ms-excel':
				return size + File.separator + 'ms-excel.png'
			case 'application/pdf':
				return size + File.separator + 'pdf.png'
			case 'application/msword':
				return size + File.separator + 'ms-word.png'
			case 'application/vnd.ms-powerpoint':
				return size + File.separator + 'ms-powerpoint.png'
			default:
				return size + File.separator + 'file.png'
		}
		return null;
	}

	def String readableByte() {
		int unit = 1000;
		if (size < unit) return size + " B";
		int exp = (int) (Math.log(size) / Math.log(unit));
		String pre = "kMGTPE".charAt(exp-1);
		return String.format("%.1f %sB", size / Math.pow(unit, exp), pre);
	}

	@Override
	public String toString() {
		return "Attachment{" +
				"id=" + id +
				", name='" + name + '\'' +
				", file='" + file + '\'' +
				", mimeType='" + mimeType + '\'' +
				", size=" + size +
				", dateCreated=" + dateCreated +
				", owner=" + ownerId +
				", version=" + version +
				"} ";
	}
}
