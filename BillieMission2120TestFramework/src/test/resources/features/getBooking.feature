Feature: Get booking

  Scenario: Get booking by id
    Given I set create booking request object correct
    And I send create booking http request
    When I send get booking http request
    Then Response status should be 200
    And Response body object booking matches update booking request object

