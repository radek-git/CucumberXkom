Feature: Contact

  Scenario: Complete form
    Given Close Cookie Popup when appears
    And   Form is empty
#    When I fill form using firstName = "jan" and lastName = "Nowak" and email "a@a.pl" messageCategory="Inne" questionTopic="Pozostałe" message="dupa"
    When I fill form using data
      | firstName   | lastName  | email              | phoneNumber  | messageCategory   | questionTopic      | message   |
      | Adam        | Kowalski  | akowal@gmail.com   |  555555555   | Inne              | Pozostałe          | dupa      |
    And Click privacy checkbox
    Then Submit button is blue