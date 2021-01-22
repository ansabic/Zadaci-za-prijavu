import java.lang.AssertionError

var brojKrugova = 0
var ulaz=-0
var protivnik=0
var stanjeIgrac=0
var stanjeProtivnik=0

private fun header(){
    println("\tKAMEN, SKARE, PAPIR")
    println()
}

private fun unosBrojaKrugova(){
    while(brojKrugova==0) {
        println("Unesi broj krugova igre:")
        try{
            brojKrugova = readLine().toString().toInt()
        }
        catch (e: NumberFormatException){
            brojKrugova=0
            println("Molimo upisite broj krugova igre prirodnim brojem!")
        }
        if(brojKrugova<0){
            println("Molimo upisite broj krugova igre prirodnim brojem!")
            brojKrugova=0
        }

    }
}
private fun mojOdabir(brojacRunde:Int){
    println("Tvoj odabir za rundu ${brojacRunde}:")
    while(ulaz !in 1..3){
        println("Za odabir kamena upisi '1', skara '2' i papira '3'!")
        try {
            ulaz=readLine().toString().toInt()
        }
        catch(e:java.lang.NumberFormatException){
            ulaz=0
            println("Nije upisan broj!")
        }
    }
}
private fun protivnikovOdabir(){
    protivnik= (1..3).random()
    val odabirProtivnika= when(protivnik){
        1-> "kamen"
        2->"skare"
        3->"papir"
        else -> throw AssertionError()
    }
    println("Protivnik je igrao $odabirProtivnika")
}
private fun rezultatRunde(brojacRunde:Int){
    // oduzimanjem rezultata definiramo rješenje općeg tipa (izbjegava se pisanje svih kombinacija)
    when(protivnik-ulaz){
        1,-2-> {
            stanjeIgrac++
            println("Dobili ste ovaj krug!")
        }
        -1,2->{
            stanjeProtivnik++
            println("Protivnik je dobio ovaj krug!")
        }
        0->{
            println("Izjednacena runda!")
        }
    }
    println("Rezultat ${brojacRunde}. runde:")
    println("$stanjeIgrac:$stanjeProtivnik")
    ulaz=0
}
private fun konacniRezultat(){
    when {
        stanjeIgrac>stanjeProtivnik -> println("Cestitamo, dobili ste!")
        stanjeIgrac==stanjeProtivnik -> println("Izjedaceno!")
        stanjeIgrac<stanjeProtivnik -> println("Nazalost ste izgubili!")
    }
    println("Konacni rezultat je $stanjeIgrac:$stanjeProtivnik")
}

private fun mainLoop(){
    for(brojacRunde in 1..brojKrugova){
        mojOdabir(brojacRunde)
        protivnikovOdabir()
        rezultatRunde(brojacRunde)
    }
    konacniRezultat()
}
fun main(args: Array<String>) {
    header()
    unosBrojaKrugova()
    mainLoop()
}