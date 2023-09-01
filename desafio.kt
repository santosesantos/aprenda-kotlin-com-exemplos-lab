// Variável id de usuário para auto incremento
var idUsuario = 1

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val nome: String, val id: Int = idUsuario++)

data class ConteudoEducacional(val id: Int, var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, val nivel: Nivel, val conteudos: MutableList<ConteudoEducacional> = mutableListOf<ConteudoEducacional>()) {

    val inscritos = mutableSetOf<Usuario>()
    
    fun matricular(vararg usuarios: Usuario): Boolean {
        if (usuarios.size <= 1) {
            return inscritos.add(usuarios[0])
        }

        for (usuario in usuarios) {
            if (!inscritos.add(usuario))
                return false
        }

        return true
    }
}

fun main() {
    // Criação da formação Kotlin
    val formacaoKotlin = Formacao("Kotlin", Nivel.INTERMEDIARIO)

    // Adição de Conteúdos
    formacaoKotlin.conteudos.add(ConteudoEducacional(1, "Aprendendo collections em Kotlin."))
    formacaoKotlin.conteudos.add(ConteudoEducacional(2, "Aprendendo loops em Kotlin.", 30))
    formacaoKotlin.conteudos.add(ConteudoEducacional(3, "Aprendendo exceptions em Kotlin.", duracao = 45))

    // Inscrições de alunos
    var novoUsuario = Usuario("Bruno")
    var isMatriculado: String = if (formacaoKotlin.matricular(novoUsuario)) "cadastrado(a) com sucesso!" else "já existe!"
    println("${novoUsuario.nome} ${isMatriculado}")
    
    novoUsuario = Usuario("Bruno", 1)
    isMatriculado = if (formacaoKotlin.matricular(novoUsuario)) "cadastrado(a) com sucesso!" else "já existe!"
    println("${novoUsuario.nome} ${isMatriculado}")
    
    novoUsuario = Usuario("Ana")
    isMatriculado = if (formacaoKotlin.matricular(novoUsuario)) "cadastrado(a) com sucesso!" else "já existe!"
    println("${novoUsuario.nome} ${isMatriculado}")

    isMatriculado = if (formacaoKotlin.matricular(Usuario("Bayek"), Usuario("Aya"))) "realizados com sucesso!" else "ocorreu um erro, algum usuário já existe!"
    println("Múltiplos cadastros ${isMatriculado}")

    isMatriculado = if (formacaoKotlin.matricular(Usuario("Bayek", 3), Usuario("Aya"))) "realizados com sucesso!" else "ocorreu um erro, algum usuário já existe!"
    println("Múltiplos cadastros ${isMatriculado}")

    // Relatório final
    println("\n-=Relatório final=-")
    println("Formação: ${formacaoKotlin.nome} -> ${formacaoKotlin.nivel}")
    println("Conteúdos:")
    for (conteudo in formacaoKotlin.conteudos) {
        println("\t${conteudo.toString()}")
    }
    println("Usuários matriculados:")
    for (usuario in formacaoKotlin.inscritos) {
        println("\t${usuario.toString()}")
    }
}
