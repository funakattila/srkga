# Az SRKG Alapítvány kézilabda csapat weboldalának tesztelése

## A feladatról
Jelen tesztkészlet, egy általam készített hobbiprojekt, a Sárospataki Református Kollégium Gimnáziumért Alapítvány kézilabda csapatának weboldalát teszteli.

Az elkészített tesztekkel szeretném lefedni a vizsgaremek kötelező elemeit, illetve bemutatni az Allure Report-ban rejlő tesztjelentést készítő lehetőségeket.
A tesztek JAVA nyelven íródtak, JUNIT 5-öt, Selenium-ot, használva, a tesztjelentés elkésztéséhez az Allure Reportot használtam, melynek futása a github repository frissítésekor automatikusan történik.

A tesztek elkészítésekor törekedtem, a tanult technikák minél szélesebbkörű alkalmazására. Az annotációk használatával növelve a tesztek, majd a tesztjelentés átláthatóságát, megérthetőségét. A DOM model használatával igyekeztem a rugalmasság, bővíthetőség lehetőségét megalapozni.  

## A vizsgamunka elemei

- A weboldal címe: [SRKGA - Kézilabda](http://srkgakezilabda.hu/)
- A weboldal admin belépés címe: [SRKGA - Kézilabda](http://srkgakezilabda.hu/admin)
- A tesztesetek tartalmazó táblázat: [Tesztesetek](https://docs.google.com/spreadsheets/d/17usWINlHQc322-yzI4dsEL2Y6qsqkedloQqOz0GRvz8/edit?usp=sharing)
- A vizsgaunka GitHub Repository-ja: [SRKGA repo](https://github.com/funakattila/srkga)
- A legutolsó tesztjelentés: [Allure Report](https://funakattila.github.io/srkga/42/)

## A tesztek futtatása
- A riportálás futtatható a GitHub oldalon, a kapcsolódó action újra indításával
- A projekt fájlok letöltését követően, megfelelő tesztkörnyezetben, saját számítógépről (Ebben az esetben, bizonyos teszteknél, szükség lehet az elvárt eredmények módosítására)
