Feature: Update booking

  Scenario: Update booking
    Given I set create booking request object correct
    And I send create booking http request
    And I have authorisation data
    When I send update booking http request
    Then Response status should be 200
    And Response body object booking matches update booking request object

  Scenario Outline: Update booking fails due to wrong date
    Given I set create booking request object <scenario>
    And I send create booking http request
    And I have authorisation data
    And I have previously created booking
    And I change dates to <checkIn> and <checkOut>
    When I send update booking http request
    Then Should throw exception
    Examples:
      | scenario    | checkIn    | checkOut   |
      | text        | ASD        | BSD        |
      | checkin     | 2121-01-10 | 2121-01-01 |
      | wrongformat | 2121-01    | 2121-01    |
      | null        | ""         | ""         |