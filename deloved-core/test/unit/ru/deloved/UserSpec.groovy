package ru.deloved

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test empty user creating"() {
    	when: 'no username or password'
    	def user=new User()
        def profile=new Profile()

    	
    	then: 'save() returns null'
    	!user.save()
    }
    
    void "test simple user creating"() {
    	when: 'username and password sets'
    	def user=new User(username: 'user', password: 'pass', enabled: true)
    	
    	then: 'save() returns true'
    	user.save()
    	user.id != null
    	
    	when: 'find created by id'
    	def foundUser=User.get(user.id)
    	
    	then: ' username iss user'
    	foundUser.username == 'user'
    	foundUser.dateCreated != null
    	
    	when: 'two users with same username'
    	def user2=new User(username: 'user', password: 'pass', enabled: true)
    	
    	then: 'save second must fail'
    	!user2.save()
    }
}
