Feature: Create booking

  Scenario: Create booking
    Given I set create booking request object correct
    When I send create booking http request
    Then Response status should be 200
    And Response contains correct booking information
    And Response has non-null id value

  Scenario Outline: Create booking fails due to malformed total price
    Given I set create booking request object <scenario>
    And Set total price to <totalPrice>
    When I send create booking http request
    Then Response status should be <statusCode>
    Examples:
      | totalPrice | scenario | statusCode |
      | -50        | negative | 400        |
      | ASD        | text     | 400        |
      | 10         | number   | 200        |

