def call() {
  return [
    enabled: true,
    sonarqube: [
      sonarqube_url: "http://sonarqube:9000"
    ],
    fail_build_qualitycheck: true,
    skip_build: false,
    fail_execution_failure: true
  ]
}
