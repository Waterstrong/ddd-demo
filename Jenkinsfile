pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Unit Test') {
          steps {
            echo 'Unit Test'
          }
        }
        stage('Integration Test') {
          steps {
            echo 'Integration Test'
          }
        }
        stage('Checkstyle') {
          steps {
            echo 'Checkstyle'
          }
        }
        stage('Sonar') {
          steps {
            echo 'Sonar'
          }
        }
        stage('Unit Test') {
          steps {
            echo 'Unit Test'
          }
        }
      }
    }
    stage('Publish') {
      steps {
        echo 'Publish'
      }
    }
    stage('Deploy') {
      steps {
        echo 'Deploy'
      }
    }
  }
}