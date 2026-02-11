import groovy.json.JsonSlurperClassic

def call() {
  echo "[commonConfig] loading from resources/common_config.json"
  def txt = libraryResource('common_config.json')
  return new JsonSlurperClassic().parseText(txt)   // returns HashMap (serializable)
}
