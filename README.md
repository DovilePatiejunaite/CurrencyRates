# CurrencyRates
JDK8

Programa skirta atsisiųsti valiutų kursus iš www.lb.lt ir apskaičiuoti kursų pokytį pagal pasirinktą laikotarpį.
Nepasirinkus laikotarpio - išvedami šiandienos valiutų kursai. 
Norint peržiūrėti pasirinkto laikotarpio valiutų kursus - pridedama opcija -d.
Pasirinkus laikotarpį, kurio metu nebuvo skelbiami valiutų kursai - parenkamas paskutinis skelbtas valiutos kursas.
Jei pasirinktas laikotarpis, kurio metu dar nebuvo pradėti skelbti valiutų kursai - parenkamas 2014-09-30 dienos valiutos kursas.

Paleidimas:

javac Main.java

java Main <valiutų kursų kodai> -d <laikotarpio_pradžia>/<laikotarpio_pabaiga>

Pavyzdžiai:
1. Nepasirenkant -d opcijos, laikotarpiui pridėti
java Main USD AUD
2. Pasirinkus opciją d
java Main USD AUD -d 2018-05-09/2018-05-14
