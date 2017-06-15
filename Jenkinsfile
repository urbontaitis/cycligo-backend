node {

  deleteDir()

  stage('Checkout source') {
    checkout scm
  }

  stage('Build project without tests') {
    sh './gradlew -i build -x test'
  }

  stage('Archive artifacts') {
    archiveArtifacts artifacts: 'build/libs/*.jar'
  }

}
