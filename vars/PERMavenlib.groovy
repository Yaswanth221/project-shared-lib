def call(Map cfg = [:]) {
    echo "[PERMavenlib] start"

    if (!cfg?.name) {
        error("[PERMavenlib] name is required")
    }

    def branch = env.BRANCH_NAME ?: "local"
    def goal = (branch == "main" || branch == "master") ? "package" : "test"

    echo "[PERMavenlib] app=${cfg.name}"
    echo "[PERMavenlib] branch=${branch}"
    echo "[PERMavenlib] selected goal=${goal}"

    // Stage 1: Checkout
    gitCheckoutScm()

    // Stage 2: Maven build (execution delegated)
    mavenLib(goal: goal, options: "-U")

    echo "[PERMavenlib] end"
}
