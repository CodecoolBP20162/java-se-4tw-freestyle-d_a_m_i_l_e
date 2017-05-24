DROP TABLE IF EXISTS food;

CREATE TABLE food (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50),
  ingredients TEXT,
  recipe TEXT,
  category VARCHAR(20),
  imageName VARCHAR(40)
);


INSERT INTO food(name, ingredients, recipe, category, imageName) VALUES (
    'Dalí spaghetti',
    '125g spaghetti (ennek a fele fekete spaghetti)
4-6 db királyrák, megtisztítva
4 db koktélparadicsom, sárga és piros vegyesen, félbevágva
2 gerezd fokhagyma, felaprítva
fél chilipaprika apróra vágva, vagy szárított chili/ cayenne bors
Grana Padano sajtforgácsok
olívaolaj, vaj',
    'A spaghettit főzd al dentére, amíg fő a tészta, el is készítheted a rákot, pár perc az egész.
Egy serpenyőben hevíts fel egy diónyi vajat olívaolaj társaságban.
Dobd a vaj-olívaolaj egyveleghez a rákokat, a fokhagymát, a chilit, sózd, borsozd, pirítsd 2-3 percig.
Ezután jöhetnek a koktélparadicsomok is, hőkezeld még 1-2 percig, és gyakorlatilag már kész is.
Keverd össze a tésztát a rákkal, szórd meg a sajtforgácsokkal, és már eheted is',
    'Main course',
  'dalispaghetti.jpg'
);

INSERT INTO food(name, ingredients, recipe, category, imageName) VALUES (
  'Csokiláva',
  '100 g étcsokoládé (legalább 60%-os)
70g vaj
2 tojás
4 ek. cukor
2 púpozott ek. liszt
csipet só',
  '1)A sütőt melegítsd elő 190°C-ra. Vajazd ki a sütőformákat.
2)Az étcsokoládét a vajjal együtt vízgőz felett olvaszd meg, és kavargasd egy fakanállal,
míg homogén állagú nem lesz.
3)A tojásokat a cukorral és a liszttel keverd habosra.
4)Vékony sugárban öntsd az olvadt vajas csokoládét a tojásos masszához,
adj hozzá egy nagyobb csipet sót is, ez kiemeli a csoki ízét, majd keverd össze.
5)Öntsd a masszát 4 kivajazott szufléformába, kb. a formák ¾-ig. Süsd kb. 10 percig, vagy
amíg meg nem reped a sütik teteje. Amint megreped a tetejük, rögtön vedd is ki a sütőből,
hiszen fontos, hogy a belsejük még lágy, krémes, kanalazható maradjon.
6)Tálald még forrón, friss gyümölcsökkel, gyümölcsszószokkal. Jó étvágyat!',
  'Dessert',
  'csokilava.jpg'
);

INSERT INTO food(name, ingredients, recipe, category, imageName) VALUES (
  'Wokos pirított rizstészta zöldségekkel',
  '1 evőkanál olaj
1 gerezd fokhagyma, finomra aprítva
2 cm friss gyömbér, finomra aprítva
1/2 chilipaprika, kimagozva, finomra aprítva
2 szál újhagyma, felkarikázva (a zöldjét a tetejére tartalékoljuk)
1/2 kaliforniai paprika, vékony csíkokra szeletelve
1/4 fej kelkáposzta, vékony csíkokra szeletelve
1 teáskanál currypor
1 evőkanál szójaszósz
1 teáskanál nádcukor
1 evőkanál szezámolaj
1 dl alaplé, vagy víz
5 dkg rizstészta',
  'Először előkészítjük az összes zöldséget: finomra aprítjuk a fokhagymát,
gyömbért, chilit, felkarikázzuk az újhagymát, felcsíkozzuk a kaliforniai paprikát,
káposztát. Az összes hozzávalót kikészítjük a konyhapultra, hogy közben ne kelljen
keresgélni, mert az étel nagyon gyorsan elkészül. Felhevítünk egy wokot, vagy serpenyőt.
Amikor forró, hozzáadjuk az olajat, majd rögtön a finomra aprított fokhagymát, gyömbért
és chilit. Néhány másodpercig pirítjuk, majd hozzáadjuk a felkarikázott újhagymát,
a felcsíkozott kaliforniai paprikát és kelkáposztát. Néhány percig dobva-rázva pirítjuk,
amíg elveszíti nyersességét, de még ropogós. Ekkor megszórjuk a curryporral, hozzáadjuk a
szójaszószt, nádcukrot és szezámolajat. Elkeverjük, majd máris jöhet rá a rizstészta, és
az alaplé (vagy víz). A tészta először száraz és merev, de néhány perc múlva megpuhul,
és belefő a szószba. Addig pirítjuk magas hőfokon, amíg a tészta az összes folyadékot
felszívja, ez összesen 4-5 perc lesz. Azonnal tálaljuk, pirított szezámmaggal
és korianderrel szórjuk meg.',
  'Main course',
  'wok.jpg'
);

INSERT INTO food(name, ingredients, recipe, category, imageName) VALUES (
  'Zöldborsókrémleves',
  '80 dkg mélyhűtött zöldborsó
1 l csirkealaplé
2 dkg vaj
1 gerezd fokhagyma
só
bors',
  'A vajon pirítsd meg a zúzott fokhagymát, majd tedd bele a zöldborsót.
Néhány percig párold, és öntsd hozzá az alaplevet. Ezt akár leveskockából is elkészítheted. Forrald fel
a levest, majd kisebb lángon főzd puhára a borsót körülbelül 15 perc alatt. Ha kész, le is veheted a tűzről.

Hagyd egy kicsit hűlni, majd robotgéppel keverd krémesre. Arra vigyázz, hogy ne turmixold túl pépesre.
Öntsd vissza a lábosba, és ízlés szerint fűszerezd sóval, borssal, majd tálalás előtt melegítsd fel.
Sajttal, pirított zsemlekockával is kínálhatod. Ha nem vagy szigorú diétán, egy kis tejfölt is tehetsz rá.',
  'Starter/Soup',
  'leves.jpg'
);

INSERT INTO food(name, ingredients, recipe, category, imageName) VALUES (
  'Szaftos-lucskos steak',
  'steak',
  'Ez egy troll recept, süsd ahogy akarod.',
  'Main course',
  'steak.jpg'
);