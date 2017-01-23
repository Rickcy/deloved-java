package ru.deloved.admin


import grails.test.mixin.*
import ru.deloved.admin.MeasureController
import spock.lang.*

@TestFor(MeasureController)
@Mock(Measure)
class MeasureControllerSpec extends Specification {

	def populateValidParams(params) {
		assert params != null
		// TODO: Populate valid properties like...
		//params["name"] = 'someValidName'
	}

	void "Test the index action returns the correct model"() {

		when: "The index action is executed"
		controller.index()

		then: "The model is correct"
		!model.measureInstanceList
		model.measureInstanceCount == 0
	}

	void "Test the create action returns the correct model"() {
		when: "The create action is executed"
		controller.create()

		then: "The model is correctly created"
		model.measureInstance != null
	}

	void "Test the save action correctly persists an instance"() {

		when: "The save action is executed with an invalid instance"
		def measure = new Measure()
		measure.validate()
		controller.save(measure)

		then: "The create view is rendered again with the correct model"
		model.measureInstance != null
		view == 'create'

		when: "The save action is executed with a valid instance"
		response.reset()
		populateValidParams(params)
		measure = new Measure(params)

		controller.save(measure)

		then: "A redirect is issued to the show action"
		response.redirectedUrl == '/measure/show/1'
		controller.flash.message != null
		Measure.count() == 1
	}

	void "Test that the show action returns the correct model"() {
		when: "The show action is executed with a null domain"
		controller.show(null)

		then: "A 404 error is returned"
		response.status == 404

		when: "A domain instance is passed to the show action"
		populateValidParams(params)
		def measure = new Measure(params)
		controller.show(measure)

		then: "A model is populated containing the domain instance"
		model.measureInstance == measure
	}

	void "Test that the edit action returns the correct model"() {
		when: "The edit action is executed with a null domain"
		controller.edit(null)

		then: "A 404 error is returned"
		response.status == 404

		when: "A domain instance is passed to the edit action"
		populateValidParams(params)
		def measure = new Measure(params)
		controller.edit(measure)

		then: "A model is populated containing the domain instance"
		model.measureInstance == measure
	}

	void "Test the update action performs an update on a valid domain instance"() {
		when: "Update is called for a domain instance that doesn't exist"
		controller.update(null)

		then: "A 404 error is returned"
		response.redirectedUrl == '/measure/index'
		flash.message != null


		when: "An invalid domain instance is passed to the update action"
		response.reset()
		def measure = new Measure()
		measure.validate()
		controller.update(measure)

		then: "The edit view is rendered again with the invalid instance"
		view == 'edit'
		model.measureInstance == measure

		when: "A valid domain instance is passed to the update action"
		response.reset()
		populateValidParams(params)
		measure = new Measure(params).save(flush: true)
		controller.update(measure)

		then: "A redirect is issues to the show action"
		response.redirectedUrl == "/measure/show/$measure.id"
		flash.message != null
	}

	void "Test that the delete action deletes an instance if it exists"() {
		when: "The delete action is called for a null instance"
		controller.delete(null)

		then: "A 404 is returned"
		response.redirectedUrl == '/measure/index'
		flash.message != null

		when: "A domain instance is created"
		response.reset()
		populateValidParams(params)
		def measure = new Measure(params).save(flush: true)

		then: "It exists"
		Measure.count() == 1

		when: "The domain instance is passed to the delete action"
		controller.delete(measure)

		then: "The instance is deleted"
		Measure.count() == 0
		response.redirectedUrl == '/measure/index'
		flash.message != null
	}
}
