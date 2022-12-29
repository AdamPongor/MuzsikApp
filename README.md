# Házi feladat specifikáció

Információk [itt](https://viauac00.github.io/laborok/hf)

## Mobil- és webes szoftverek
### 2022.10.18.
### ZeneApp
### Pongor Ádám - (HA9VZU)
### adam.pongor14@gmail.com
### Laborvezető: Reiter Márton

## Bemutatás

Még mielőtt bármit is tudtam volna programozásról, egy MIT app inventor (kb. scratch androidra) nevű webes fejlesztőkörnyezettel csináltam egy gitáros zene író/gyakorló alkalmazást. A cél az volt, hogy ne kelljen gondolkodnom, vagy feleslegesen keresgélnem interneten, hanem legyen minden info, amire szükségem van egy helyen. Nagyon jól működött (bár elég primitív módon), az egyetlen probléma vele, hogy jelenleg nem tudom bővíteni, mert ha bármit is csinálok vele, kifagy a weboldal, ahogy megpróbálja betölteni a 8000 blokkot:
![](8000.png)
Ezt az alkalmazást szeretném újraírni és bővíteni értelmesen, tanult technológiákkal.

## Főbb funkciók

-Ha írunk valami zenére hasonlítót, de nem tudjuk milyen kulcsban van, bekattintgathatjuk a hangokat, és megkeresi, hogy ezek az alkalmazásban szereplő skálák közül melyikekben vannak benne:
![](Screenshot_20221009-173909.jpg)
A választás után kiemeli az összes ide tartozó hangot (a következő pontban látszik, hogy ez hogy néz ki)

-Ha tudjuk, milyen skála hangjait szeretnénk megnézni, az Alaphang, Skála/Mód és Mutat gombokkal tudunk választani:
![](Screenshot_20221009-173938.jpg)

-Az Akkordok gomb megmutatja a skálába tartozó akkordokat:
![](Screenshot_20221009-174005.jpg)

-A törlés egyszerűen visszarakja a képernyőt alaphelyzetbe:
![](Screenshot_20221009-173846.jpg)

-A Váltás Rózsafára pedig egy afféle sötét/világos mód kapcsoló:
![](Screenshot_20221009-174026.jpg)

Új funkciók, amiket szeretnék megvalósítani:

-Valamivel szebb és értelmesebb UI

-A képernyőn jelenleg csak egy gitár fogólapjának fele van, mert a hangok onnan ismétlődnek (ezért folytatódnak alul a fent elkezdett számok), de jobb lenne, ha a teljes nyak látszódna és scrollozható layoutban lenne

-Lesz lehetőség a hangolás kiválasztására húronként, vagy akár több húrt is hozzá lehet adni. A felhasználó mentheti az általa készített hangolásokat, amik perzisztensen tárolódnak és RecyclerViewban jelennek meg az erre szolgáló képernyőn.

## Választott technológiák:

- UI
- Fragmentek
- RecyclerView
- Perzisztens adattárolás
- Stílusok/témák