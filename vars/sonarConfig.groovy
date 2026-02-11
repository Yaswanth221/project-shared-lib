def call() {
  return [
    enabled: true, // set false to see sonar disabled
    sonarqube: [
      sonarqube_url: "http://localhost:9000"
    ],
    fail_build_qualitycheck: false,
    skip_build: false,
    fail_execution_failure: false
  ]
}
