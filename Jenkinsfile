projects = [
  'android-launcher', 
  'android-ikaros', 
  'android-yumi', 
  'meteor-sisyfos'
]

pipeline {
  agent any
	
  environment {
    ANDROID_HOME = '/var/lib/jenkins/Android/Sdk' 
  }

  stages {
    stage('Init') {
      steps {
        echo 'Init...'
        projects.each {project -> sh "${project}/palingenesis init"}
      }
    }
    stage('Clean') {
      steps {
        echo 'Cleaning...'
        sh  "android-launcher/palingenesis clean"
        sh    "android-ikaros/palingenesis clean"
        sh      "android-yumi/palingenesis clean"
        sh    "meteor-sisyfos/palingenesis clean"
        //sh "meteor-waterplant/palingenesis clean"
      }
    }
    stage('Build') {
      steps {
        echo 'Building...'
        sh  "android-launcher/palingenesis build"
        sh    "android-ikaros/palingenesis build"
        sh      "android-yumi/palingenesis build"
        sh    "meteor-sisyfos/palingenesis build"
        //sh "meteor-waterplant/palingenesis build"
      }
    }
    stage('Test') {
      steps {
        echo 'Testing...'
        sh  "android-launcher/palingenesis test"
        sh    "android-ikaros/palingenesis test"
        sh      "android-yumi/palingenesis test"
        sh    "meteor-sisyfos/palingenesis test"
        //sh "meteor-waterplant/palingenesis test"
      }
    }
    stage('Analysis') {
      steps {
        echo 'Analysis...'
        sh  "android-launcher/palingenesis analysis"
        sh    "android-ikaros/palingenesis analysis"
        sh      "android-yumi/palingenesis analysis"
        sh    "meteor-sisyfos/palingenesis analysis"
        //sh "meteor-waterplant/palingenesis analysis"
      }
    }
    stage('Deploy') {
      steps {
        echo 'Deploying...'
        sh  "android-launcher/palingenesis deploy"
        sh    "android-ikaros/palingenesis deploy"
        sh      "android-yumi/palingenesis deploy"
        sh    "meteor-sisyfos/palingenesis deploy"
        //sh "meteor-waterplant/palingenesis deploy"
      }
    }
  }
}
