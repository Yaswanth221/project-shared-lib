def call(Map cfg = [:]) {
  echo "[PERMavenlib] start"

  if (!cfg?.name) {
    error("[PERMavenlib] name is required")
  }

  def _commonConfig = commonConfig()
  def _sonarConfig = sonarConfig()

  // choose goal (your existing standard)
  def branch = env.BRANCH_NAME ?: "local"
  def goal = (branch == "main" || branch == "master") ? "package" : "test"

  echo "[PERMavenlib] app=${cfg.name}"
  echo "[PERMavenlib] branch=${branch}"
  echo "[PERMavenlib] selected goal=${goal}"

  // checkout (your existing standard)
  gitCheckoutScm()

  // ---- Company-recipe delegation (idea implementation) ----
  // If sonar disabled, pass null sonarUrl so mavenLib skips it
  if (!_sonarConfig.enabled) {
    echo "[PERMavenlib] sonar disabled"
    _sonarConfig.sonarqube.sonarqube_url = null
  } else {
    echo "[PERMavenlib] sonar enabled"
  }

  // Call mavenLib in "company recipe mode"
  mavenLib(
    mavenGoals: [release: 'deploy', develop: 'deploy', other: goal, all: 'clean'],
    mavenOptions: [all: ['-U']],
    settingsXml: _commonConfig.maven.settings_xml,
    sonarUrl: _sonarConfig.sonarqube.sonarqube_url,
    gitCredentials: _commonConfig.git_ssh_credentials,
    gitEmail: _commonConfig.maven.email,
    gitUser: _commonConfig.maven.user,
    sonarScanOverrides: [
      failOnQualityGate: _sonarConfig.fail_build_qualitycheck,
      skipBuild: _sonarConfig.skip_build,
      failOnExecutionFailure: _sonarConfig.fail_execution_failure
    ],
    secretsScan: _commonConfig.secret_scan_enable,
    scaScan: _commonConfig.sca_scan_enable == false ? false : [
      build: _commonConfig.sca_scan_enable,
      maven: [settingsXml: _commonConfig.maven.settings_xml]
    ]
  )

  echo "[PERMavenlib] end"
}
