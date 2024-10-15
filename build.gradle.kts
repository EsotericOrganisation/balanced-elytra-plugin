import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
  `java-library`
  id("io.papermc.paperweight.userdev") version "1.7.1"
  id("xyz.jpenilla.run-paper") version "2.3.0"
  id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.1.1"
}

val groupStringSeparator = "."
val kebabcaseStringSeparator = "-"
val snakecaseStringSeparator = "_"

fun capitaliseFirstLetter(string: String): String {
  return string.first().uppercase() + string.slice(IntRange(1, string.length - 1))
}

fun snakecase(kebabcaseString: String): String {
  return kebabcaseString.lowercase().replace(kebabcaseStringSeparator, snakecaseStringSeparator).replace(" ", snakecaseStringSeparator)
}

fun pascalcase(kebabcaseString: String): String {
  var pascalCaseString = ""

  val splitString = kebabcaseString.split(kebabcaseStringSeparator)

  for (part in splitString) {
    pascalCaseString += capitaliseFirstLetter(part)
  }

  return pascalCaseString
}

val mainProjectAuthor = "Esoteric Organisation"
val topLevelDomain = "org"
val projectAuthors = listOfNotNull(mainProjectAuthor, "rolyPolyVole", "Esoteric Enderman")

description = "A simple Minecraft plugin that prevents the use of rocket-boosted elytra in the overworld. Made for a private server."

group = topLevelDomain + groupStringSeparator + "esoteric"
version = "1.0.0-SNAPSHOT"

val javaVersion = 21
val paperApiVersion = "1.21"

java {
  toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
}

dependencies {
  paperweight.paperDevBundle("$paperApiVersion-R0.1-SNAPSHOT")
}

tasks {
  compileJava {
    options.release = javaVersion
  }

  javadoc {
    options.encoding = Charsets.UTF_8.name()
  }
}

bukkitPluginYaml {
  name = "BalancedElytra"
  description = project.description
  authors = projectAuthors

  version = project.version.toString()
  apiVersion = paperApiVersion
  main = project.group.toString() + groupStringSeparator + "minecraft.plugins.elytra.balance" + groupStringSeparator + pascalcase(rootProject.name)
  load = BukkitPluginYaml.PluginLoadOrder.STARTUP
}
