Feature: Create, update, get and delete a booking.

  Background: Service api endpoints are set

  Scenario: Create booking
    Given I set create booking request body json
    When I send create booking http request
    Then Response status should be 200
    And Response body object booking matches create booking request object
    And Response body object has non-null id value

  Scenario: Update booking
    Given I have previously created booking
    And I have authorisation data
    When I send update booking http request
    Then Response status should be 200
    And Response body object booking matches update booking request object

  Scenario: Get booking by id
    When I send get booking http request
    Then Response status should be 200
    And Response body object booking matches update booking request object

  Scenario: Delete booking by id
    Given I have authorisation data
    When I send delete booking http request
    Then Response status should be 201
    When I send get booking http request
    Then Response status should be 404



