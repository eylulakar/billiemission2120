Feature:Delete booking.

  Scenario: Delete booking by id
    Given I set create booking request object correct
    And I send create booking http request
    Given I have authorisation data
    When I send delete booking http request
    Then Response status should be 201
    When I send get booking http request
    Then Should throw exception



