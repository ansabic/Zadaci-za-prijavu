 var unos=""

private fun header(){
    println("PALINDROM")
    println()
}
private fun unosRijeci(){
    while(unos.isBlank()) {
        println("Unesite tekst za provjeru palindroma:")
        unos = readLine().toString()
        //Format koji odabire samo slova engleskog alfabeta! (bez č, ć, š...)
        val format = Regex("[^a-zA-Z]")
        //Brise se sve osim odabranog formata (slova)
        unos = format.replace(unos, "")
        unos = unos.toLowerCase()
        if(unos.isBlank())
            println("Unos je prazan ili nisu podrzani uneseni znakovi, pokusajte opet!")
    }
}
private fun provjeraPalindroma(){
    val obrnutiUnos=unos.reversed()
    when {
        unos==obrnutiUnos -> println("Palindrom")
        unos.isBlank() -> println("Krivi unos!")
        else -> println("Nije palindrom")
    }
    unos=""
}
private fun mainLoop(){
    //Beskonacna petlja da se ne pokrece program svaki put za provjeru
    while(true){
        unosRijeci()
        provjeraPalindroma()
    }
}
fun main(args: Array<String>){
    header()
    mainLoop()
}