pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage("Compile") {
            steps {
                bat "./gradlew compileJava"
            }
        }
        stage("Unit test") {
            steps {
                bat "./gradlew test"
            }
        }
        stage("Code coverage") {
            steps {
        	    bat "./gradlew jacocoTestReport"
        	 	publishHTML (target: [
         	        reportDir: 'build/reports/jacoco/test/html',
         			reportFiles: 'index.html',
         			reportName: 'JacocoReport'
         	    ])
         		bat "./gradlew jacocoTestCoverageVerification"
         	}
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQubePruebas') {
                    bat './gradlew sonarqube -Dsonar.login=05b61c2d1cb657800474499bdf8f1b787c25413f'
                }
            }
        }
    }
}
