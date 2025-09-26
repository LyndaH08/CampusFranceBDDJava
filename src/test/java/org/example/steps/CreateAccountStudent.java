package org.example.steps;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateAccountStudent {
    WebDriver driver;
    WebElement dropdownInput;


        @Given("L'utilisateur est sur la page de creation du compte 'CampusFrance'")
            public void lancerFormulaire() {
            System.out.println("Step executed");
            // Création des options pour configurer chrome le navigateur Chrome
            ChromeOptions options = new ChromeOptions();

            // Vérifie si le test s'exécute sur GitHub Actions
            // GitHub définit automatiquement la variable d'environnement GITHUB_ACTIONS à "true"
            String githubActions = System.getenv("GITHUB_ACTIONS");
            if ("true".equals(githubActions)) {
                // Exécution en mode headless (pas d'affichage graphique)
                // Obligatoire sur CI pour éviter les erreurs de session ou d'affichage
                options.addArguments("--headless=new");
                // Désactive le sandboxing (nécessaire pour certains environnements CI)
                options.addArguments("--no-sandbox");
                // Permet à Chrome de fonctionner correctement sur des runners avec peu de mémoire
                options.addArguments("--disable-dev-shm-usage");
                // Fix pour la taille d'écran afin que tous les éléments soient visibles
                //options.addArguments("--window-size=1920,1080");
            }

            // Création du driver Chrome avec les options définies ci-dessus
            driver = new ChromeDriver(options);
            // Lancer la page du formulaire
            driver.get("https://www.campusfrance.org/fr/user/register");

        }

            @When("L'utilisateur renseigne les champs {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
            public void renseignerChamps (String AdresseMail, String MotDePasse, String civilite, String Nom, String Prenom,
                    String PaysResidence, String PaysNationalite, String Ville, String CodePostale,
                    String Telephone, String statut, String Domaine, String Niveau){
                //Renseigner les champs de saisie du formulaire
                //Informations de connexion
                driver.findElement(By.xpath("//input[@placeholder='monadresse@domaine.com']")).sendKeys(AdresseMail);
                driver.findElement(By.cssSelector("#edit-pass-pass1")).sendKeys(MotDePasse);
                driver.findElement(By.cssSelector("#edit-pass-pass2")).sendKeys(MotDePasse);

                //Informations personnelles
                // Accepter cookies
                driver.findElement(By.id("tarteaucitronPersonalize2")).click();

                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();",
                        driver.findElement(By.id("edit-field-civilite-mr")));

                driver.findElement(By.id("edit-field-nom-0-value")).sendKeys(Nom);
                driver.findElement(By.id("edit-field-prenom-0-value")).sendKeys(Prenom);

                //Selectionner le pays de résidence
                // C'est un Input et pas un Select = récupere l'element, vider le champ, taper le pays, et entrer
                dropdownInput = driver.findElement(By.id("edit-field-pays-concernes-selectized"));
                dropdownInput.sendKeys(Keys.BACK_SPACE);
                dropdownInput.sendKeys(PaysResidence);
                dropdownInput.sendKeys(Keys.ENTER);

                driver.findElement(By.id("edit-field-nationalite-0-target-id")).sendKeys(PaysNationalite);
                driver.findElement(By.id("edit-field-code-postal-0-value")).sendKeys(CodePostale);
                driver.findElement(By.id("edit-field-ville-0-value")).sendKeys(Ville);
                driver.findElement(By.id("edit-field-telephone-0-value")).sendKeys(Telephone);

                //Informations complémentaire
                //Scroller  avant de cocher le bouton radio  Etudiant
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();",
                        driver.findElement(By.id("edit-field-publics-cibles-2")));

                //C'est un Input et pas un Select = récupere l'element, vider le champ, taper le pays, et entrer
                dropdownInput = driver.findElement(By.id("edit-field-domaine-etudes-selectized"));
                dropdownInput.sendKeys(Keys.BACK_SPACE);
                dropdownInput.sendKeys(Domaine);
                dropdownInput.sendKeys(Keys.ENTER);

                dropdownInput = driver.findElement(By.id("edit-field-niveaux-etude-selectized"));
                dropdownInput.sendKeys(Keys.BACK_SPACE);
                dropdownInput.sendKeys(Niveau);
                dropdownInput.sendKeys(Keys.ENTER);

            }

            @When("L'utilisateur clique sur le bouton valider")
            public void valider () {
            }

            @Then("le {string} est bien selectioner")
             public void NiveauSelectionner (String NiveauAttendu) {
                // Vérifier que le niveau est bien selectionnet et c'est bien "Licence 1".
                // Recupere le text de l'item qui est le text selectionner
                WebElement champ = driver.findElement(By.cssSelector("#edit-field-niveaux-etude-wrapper > div > div > div.selectize-input.items.has-options.full.has-items > div"));

                //Vérifie que le champ n'est pas vide
                assertFalse(champ.getText().isEmpty(), "Le champ ne doit pas être vide");
                //Vérifie le texte attendu
                assertEquals(NiveauAttendu, champ.getText(), "Le texte du champ est incorrect");
                           }


            @Then("le message \"Le compte a bien été créé\" apparait")
            public void verifierMessage (){
                //Vérifier l'intituler du label du bouton de validation du formulaire
                assertEquals("Créer un compte", driver.findElement(By.id("edit-submit")).getAttribute("value"));

                // Vérifier que le statut sélectionné est "Etudiant"
                assertTrue(driver.findElement(By.id("edit-field-publics-cibles-2")).isSelected(),"Le bouton 'Étudiant' n'est pas sélectionné.");
                driver.quit();
        }

}