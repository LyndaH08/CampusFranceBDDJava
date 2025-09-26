pipeline {
    agent any

    tools {
        maven 'Maven 3.9.11'   // Nom configuré dans Jenkins
        jdk 'Java 21'      // Nom configuré dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Récupérer le code depuis le repo Git
                git branch: 'main', url: 'https://github.com/LyndaH08/CampusFranceBDDJava.git'
            }
        }

         stage('Build') {
            steps {
                bat "mvn clean compile"
            }
        }

        stage('Test') {
            steps {
                bat "mvn test"
            }
        }
    }
}
