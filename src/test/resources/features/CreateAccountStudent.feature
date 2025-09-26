Feature: Crée un compte etudiant

  Scenario Outline: Tester la creation d'un compte etudiant
    Given L'utilisateur est sur la page de creation du compte 'CampusFrance'
    When L'utilisateur renseigne les champs "<AdresseMail>", "<MotDePasse>", "<Civilité>", "<Nom>", "<Prenom>", "<PaysResidence>", "<PaysNationalite>", "<Ville>", "<CodePostale>", "<Telephone>", "<Statut>", "<Domaine>", "<Niveau>"
    And L'utilisateur clique sur le bouton valider
    Then le "<NiveauAttendu>" est bien selectioner
    And le message "Le compte a bien été créé" apparait

    Examples:
      | AdresseMail     | MotDePasse  | Civilité | Nom    | Prenom | PaysResidence | PaysNationalite | Ville  | CodePostale | Telephone   | Statut   | Domaine       | Niveau          |NiveauAttendu    |
      | Sam@gmail.com   | Azerty123   |mr       | Dupont | sam    | France        | France          | Paris  | 75006       | 0612345678  | Etudiant | Sport         | Licence 1        | Licence 1       |
      | Linda@gmail.com | Qwerty456   |mme      | Martin | Lin    | France        | Algerie         | Lyon   | 69001       | 0698765432  | Etudiant | Biologie      | Doctorat / PhD   | Doctorat / PhD  |



