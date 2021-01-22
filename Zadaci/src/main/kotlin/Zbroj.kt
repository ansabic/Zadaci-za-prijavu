import java.lang.NumberFormatException

var listaBrojeva= arrayListOf<Int>()
var trenutnaGrana=arrayListOf<Int>()
var ciljaniBroj=0
var brojacKombinacija=0
var rjesenja=arrayListOf<Int>()

private fun zavrsiGranu(){

    // Ovom funkcijom brisemo od kraja clanove grane sve dok su jednaki vrijednosti zadnjeg clana u odabranoj listi
    // brojeva(while dio funkcije) i jos jedan dodatni clan grane(if dio funkcije). Time omogucujemo programu da zavrsi
    // provjeru te grane i krene s provjerom sljedece grane.

    // Ne mozemo obgrlit cijelu funkciju sa if-om koji u argumentu ima !trenutnaGrana.isNullOrEmpty() jer se remove-om moze
    // mijenjati takvo stanje pa program moze javiti exception. Zato se prije svakog brisanja clana arrayList-e(grane)
    // provjerava je li lista prazna.

    while(!trenutnaGrana.isNullOrEmpty() && trenutnaGrana.last()==listaBrojeva.last()){
        trenutnaGrana.removeLast()
        brojacKombinacija--
    }
    if(!trenutnaGrana.isNullOrEmpty()){
        trenutnaGrana.removeLast()
        brojacKombinacija--
    }
}
private fun kombiniraj(x:Int) {
    for(i in x until listaBrojeva.size) {
        trenutnaGrana.add(listaBrojeva[i])
        brojacKombinacija++
        when {
            trenutnaGrana.sum()<ciljaniBroj -> kombiniraj(i)
            trenutnaGrana.sum()==ciljaniBroj -> {
                rjesenja.add(brojacKombinacija)
                zavrsiGranu()
            }
            trenutnaGrana.sum()>ciljaniBroj -> {
                zavrsiGranu()
            }
        }
    }
}
private fun header(){
    println("KOMBINACIJE ZBROJA")
    println("")
}

private fun unos(){
    // Unos citamo kao String kojemu se okidaju potencijalno uneseni razmaci na krajevima(trim), razdjelimo taj string u sekvence
    // koje definiramo razmakom te prenesemo takve novonastale stringove(sekvence) u listu cije clanove kasnije u for petlji
    // pretvaramo u Int-ove i prebacujemo u listu izabranih brojeva

    var tempList= emptyList<String>()
    while(tempList.isNullOrEmpty()){
        try {
            println("Unesite listu prirodnih brojeva odvojenih razmakom:")
            tempList=readLine().toString().trim().splitToSequence(' ').toList()
            for(i in tempList.iterator()){
                val temp=i.toInt()
                if(temp<=0){
                    tempList= emptyList()
                    println("Moraju se unijeti prirodni brojevi!")
                }
                else
                    listaBrojeva.add(temp)
            }
        }
        catch (e:NumberFormatException){
            println("Moraju se unijeti prirodni brojevi!")
            tempList= emptyList()
        }
    }
    while(ciljaniBroj==0){
        try {
            println("Unesite broj kojem trazimo najmanji broj pribrojnika:")
            ciljaniBroj= readLine().toString().toInt()
            if(ciljaniBroj<=0){
                ciljaniBroj=0
                println("Mora se unijeti prirodni broj!")
            }
        }
        catch(e:NumberFormatException){
            println("Mora se unijeti prirodni broj!")
        }
    }
}

fun main(args: Array<String>) {
    header()
    unos()
    // pokretanje provjere grani stabla pocevsi od prvog clana liste brojeva
    kombiniraj(0)
    // sortirana rjesenja od najmanjeg prema najvecem
    rjesenja.sort()
    // print rjesenja
    if(!rjesenja.isNullOrEmpty())
        println("${rjesenja[0]}")
    else
        println("-1")
}