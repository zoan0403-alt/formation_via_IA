# Suivi de niveau — Formation Spring Boot / Git

## Contexte élève
- Apprend en mode précepteur (socratique strict).
- A écrit le CRUD initial (Produit/Livre) avec assistance IA — estimation "80% autonome" à valider concrètement, pas encore testée en pratique réelle (seulement en questions/réponses orales).
- Environnement : Windows + IntelliJ + Git Bash (MINGW64).

## Séance du 2026-09-25

### Sujets abordés
- Test diagnostic Spring Boot (couches, JPA, `ddl-auto`, query derivation)
- Git de zéro : `.git` vs `.gitignore`, les 3 zones (working dir / staging / repo), cycle add/commit/push, branches (création, isolation, merge), navigation Git Bash sous Windows

### Niveau évalué

**Spring Boot : fragile, connaissances d'usage sans les mécanismes.**
- Comprend correctement le flux en couches (Controller→Service→Repository→BD) et sait l'expliquer avec ses mots.
- Ne connaissait pas la valeur par défaut de `ddl-auto` ni où elle se configure — pensait l'avoir configurée alors que le fichier `application.properties` ne contient quasiment rien. Léger signal d'affirmation prématurée (a affirmé "j'ai mis à jour telle ligne" avant de vérifier, puis a découvert que non).
- Sur la query derivation (`findByNom`), a d'abord donné des réponses vagues ("Spring écrit les requêtes à l'exécution") mais a réussi, par une série de questions guidées, à reconstruire correctement le mécanisme complet (proxy dynamique au démarrage, parsing du nom de méthode en SELECT/WHERE/champ, erreur au démarrage et non à la compilation). **Capacité de raisonnement déductif solide une fois bien guidé — c'est un point fort à exploiter.**
- N'a pas encore vérifié lui-même la doc officielle Spring Boot sur `ddl-auto` (demandé, non confirmé en retour) — à relancer.

**Git : débutant complet au départ → bases opérationnelles acquises en une séance.**
- Aucune connaissance préalable, y compris la commande `ls`. A dû apprendre `cd`, `ls -a`, la conversion de chemins Windows→Git Bash (`D:\...` → `/d/...`) — bloqué plusieurs tours, résolu avec indices progressifs (niveau 4-5 : pointeur vers `ls --help`).
- A confondu `.gitignore` et `.git` au début (pensait que la présence de `.gitignore` prouvait le suivi Git) — corrigé après questionnement, mais cette confusion mérite d'être retestée plus tard sans aide.
- A exécuté correctement et sans erreur bloquante tout le cycle : `git status`, `git add`, `git commit -m`, `git push`, `git switch -c`, `git switch`, `git merge`, avec vérification indépendante côté serveur (GitHub) par le formateur.
- Bon réflexe observé spontanément : a remarqué `modified: .gitignore` inattendu dans `git status` avant un commit et a demandé pourquoi au lieu de commit à l'aveugle (bonne hygiène, à encourager).
- Confusion notable : a d'abord attribué l'isolation entre branches locales au fait de ne pas avoir fait `git push` (confond synchronisation locale/distante avec isolation des branches). S'est corrigé seul après reformulation de la question, en retrouvant sa propre définition antérieure de "branche = pointeur vers un commit".
- N'a pas encore vu : gestion des conflits de merge, `git pull`, `git clone`, `.gitignore` en profondeur, remotes multiples, rebase.

### Patterns d'erreur récurrents à surveiller
1. **Affirmation avant vérification** : tendance à répondre "je pense que c'est X" avec assurance avant de vérifier dans le code/la doc (vu sur `ddl-auto`, sur l'origine de la modif `.gitignore`). Ce n'est pas un manque de capacité — quand poussé à vérifier, il vérifie correctement et se corrige. À travailler : le réflexe de vérifier *avant* d'affirmer.
2. **Confusion entre concepts locaux et distants** (Git) — à retester plus tard (push/pull vs branches locales, staging vs commit).
3. Bonne honnêteté quand il ne sait vraiment pas ("je ne sais pas") plutôt que d'inventer — à préserver, c'est une qualité rare et précieuse pour l'apprentissage.

### Axes à travailler pour la prochaine séance
- Retester à froid (sans aide) : différence `.git` / `.gitignore`, rôle du staging, ce qu'un `push` fait vs ne fait pas.
- Vérifier la doc Spring Boot sur `ddl-auto` (valeur par défaut embarqué vs externe) — resté en suspens cette séance.
- Introduire : conflits de merge Git (simulation volontaire), `git log` en détail, `git clone`.
- Spring Boot : commencer à coder en autonomie réelle (pas juste expliquer) — proposer un petit ajout de fonctionnalité sur `Produit` ou `Livre` à réaliser seul, avec guidage minimal, pour valider le "80% autonome" annoncé.

### Séance du 2026-09-25 (suite) — Mise en pratique Spring Boot : recherche par fourchette de prix

Exercice réalisé en autonomie quasi complète (guidage par questions uniquement, aucun code fourni) : ajout d'un endpoint `GET /api/produits/recherche?prixMin=&prixMax=` sur les 3 couches (repository/service/controller), avec validation et test réel via Postman.

**Bugs introduits puis corrigés seul (après questionnement, jamais donné directement) :**
1. Condition `if(min >= min)` puis `if(min > min)` (comparaison d'une variable à elle-même) au lieu de `min > max` — révélateur d'un manque de rigueur à la relecture, corrigé après un tracé d'exécution à la main avec valeurs concrètes.
2. Code HTTP incohérent avec sa propre réponse orale de quelques minutes plus tôt (`NOT_FOUND` au lieu de `BAD_REQUEST`) — signe qu'il ne relit pas systématiquement son code par rapport à ses propres décisions de conception.
3. Mauvaise annotation controller : `@PathVariable` puis `@QuerydslPredicate` (inventée/confondue) au lieu de `@RequestParam` — lacune de vocabulaire Spring MVC de base, comblée par indice ciblé (pas de raisonnement possible ici, connaissance factuelle manquante).
4. **Chemin dupliqué par concaténation `@RequestMapping` (classe) + `@GetMapping` (méthode)** → route jamais atteinte, tombait sur `/{id}` avec `MethodArgumentTypeMismatchException`. Bug le plus difficile pour lui à percevoir (confirmé par l'élève lui-même en fin de séance) : chaque ligne de code est syntaxiquement correcte, l'erreur n'existe qu'au niveau de la composition entre les deux annotations. **Point de vigilance pédagogique : à retravailler explicitement (mapping Spring MVC, préfixes de classe).**
5. Erreur de syntaxe URL (`&?prixMax=` avec un `?` en trop) — étourderie, corrigée rapidement une fois pointée.
6. Slash final sur `/api/produits/` en POST alors que le mapping est `/api/produits` — bien identifié et corrigé seul après qu'on ait pointé la comparaison, bon signe de progression dans le débogage méthodique.

**Points positifs observés :**
- A mené tout le débogage (404 puis 400 puis erreurs Postman) de façon méthodique une fois la méthode de "tracer avant de conclure" imposée — n'a plus tenté de deviner à l'aveugle en fin d'exercice (ex: bug du slash final, corrigé seul).
- A correctement diagnostiqué "la base est vide" comme cause probable d'un résultat vide (raisonnement JPA/H2 correct), tout en acceptant la distinction qu'on lui a apportée entre "liste vide" (200 OK) et "route non trouvée" (404) sans s'entêter.
- A construit seul le JSON de test POST à partir de la lecture de l'entité `Produit` (bons types, bonne syntaxe), sans qu'on le lui écrive.
- Métacognition en fin d'exercice cohérente et honnête : a identifié lui-même le bug de mapping dupliqué comme le plus difficile, avec une explication correcte de pourquoi (bug de composition, pas de syntaxe).

### Séance du 2026-09-25 (suite 2) — Bean Validation (`@NotBlank`, `@Positive`, `@Valid`)

Exercice court : empêcher la création d'un `Produit` avec `nom` vide ou `prix` négatif, via `jakarta.validation.constraints`.

- A confirmé lui-même le problème initial (test manuel avant de coder — bon réflexe maintenant acquis, contrairement au tout début de la séance où il codait/affirmait sans vérifier).
- A mis `@NotBlank` sur `prix` (type `double`) par premier réflexe — erreur de mapping annotation/type, mais **a correctement interprété le message d'erreur Java lui-même** (`No validator could be found for constraint 'NotBlank' validating type 'java.lang.Double'`) pour identifier et corriger l'incohérence, sans qu'on lui donne la réponse. Bon signe : sait lire un stacktrace/message d'erreur technique en anglais et en extraire l'information utile.
- A proposé `@NotNull` avant de comprendre par lui-même (question guidée) qu'un `double` primitif ne peut jamais être `null`, donc que l'annotation serait inutile — a ensuite retrouvé seul `@Positive`.
- A ajouté `@Valid` sur le paramètre du controller **de sa propre initiative**, sans qu'on lui donne le nom de l'annotation ni sa syntaxe — signal positif fort : commence à généraliser des patterns Spring vus ailleurs (probablement retenu du travail avec l'IA en amont, mais réutilisé ici à bon escient et au bon endroit).
- A spontanément remarqué, sans qu'on le lui souffle, la différence entre les logs serveur (avec messages personnalisés) et la réponse JSON réellement envoyée au client (générique, sans les messages) — a formulé lui-même le besoin d'une gestion d'erreur personnalisée (`@ControllerAdvice`/`@ExceptionHandler`, pas encore vu). **Bonne intuition d'architecture API, spontanée.**

## Niveau global (après 1 séance, 3 exercices)

- **Git : débutant → notions de base acquises (niveau "junior encadré").** Capable d'exécuter le cycle add/commit/push/branch/merge avec supervision légère ; pas encore autonome sur la résolution de problèmes imprévus (conflits, erreurs complexes).
- **Spring Boot : le "80% autonome" annoncé en début de séance est globalement confirmé au niveau de l'exécution (a écrit repository/service/controller quasi seul), mais avec une fiabilité de première passe faible — 6 bugs sur le premier exercice (une seule méthode à 3 couches).** Sa vraie force est ailleurs : la **correction** une fois le bug pointé est rapide, le raisonnement de debug a nettement progressé en cours de séance (de "deviner" à "tracer méthodiquement" à "vérifier avant de coder"), et il commence à **transférer/généraliser** des patterns Spring déjà vus (`@Valid` ajouté sans qu'on le demande) plutôt que d'attendre chaque instruction. Lacune vocabulaire Spring MVC de base en voie de comblement.
- **Méthode de travail : point fort confirmé et en nette amélioration au fil de la séance** — bon raisonnement déductif sous guidage, honnêteté sur les lacunes, réflexe de vérification qui devient plus systématique (a vérifié le problème avant de coder sur l'exercice 3, alors qu'il ne l'avait pas fait sur l'exercice 2). Sait lire et interpréter un message d'erreur technique en anglais pour en tirer la correction. **Point à travailler la prochaine fois : relire son propre code avant de le soumettre (cohérence avec ses propres décisions de conception), notamment sur des exercices plus longs où la charge cognitive augmente.**

## Séance du 2026-09-26 — Implémentation de `@ControllerAdvice` / `@ExceptionHandler`

Suite directe du sujet identifié par l'élève lui-même en fin de séance précédente. Construction pas à pas de `GlobalExceptionHandler` (classe, annotations, extraction des erreurs de `BindingResult`, construction de la réponse).

**Progression pendant la construction :**
- A correctement déduit `@ControllerAdvice`/`@RestControllerAdvice` par analogie avec `@Controller`/`@RestController` déjà connu — bon transfert conceptuel.
- A identifié seul `@ExceptionHandler` et `getFieldErrors()` après plusieurs essais erronés mais orientés dans la bonne direction (`getRawFieldValue()`, `getSuppressedFields()` — noms plausibles, pas du hasard, montre qu'il explore l'autocomplétion avec un minimum de logique de nommage plutôt que totalement à l'aveugle).
- **Erreur de conception notable** : a d'abord déclaré `exception` comme **champ de la classe** au lieu de **paramètre de méthode** — révèle une compréhension encore fragile de la portée des variables et de comment Spring **fournit** les objets (injection de paramètre) par opposition à un champ qu'on initialiserait soi-même. À retravailler.
- A mélangé `@ControllerAdvice` et `@RestControllerAdvice` en cours de route (retour en arrière sur un choix qu'il avait lui-même justifié quelques minutes avant) — même pattern d'inattention/incohérence que lors de la séance précédente (code HTTP oublié).
- A eu besoin d'un indice appuyé sur la syntaxe `Classe.class` (référencer une classe comme valeur) — connaissance Java de base pas encore solide, comblée par explication factuelle directe (pas de raisonnement possible ici).

**Moment clé : blocage de compréhension globale, verbalisé clairement ("SACHE QUE J'AI RIEN COMPRIS").**
- L'élève a suivi correctement toutes les étapes techniques (a écrit du code syntaxiquement juste, corrigé ses propres erreurs quand pointées) **sans comprendre le sens global** de ce qu'il construisait — signal important : sur ce sujet-ci, la décomposition en petites étapes techniques a été poussée *avant* d'ancrer le concept global, ce qui a produit de l'exécution mécanique sans compréhension.
- **Réaction positive à noter** : a explicitement demandé de l'aide plutôt que de continuer à faire semblant de suivre ou d'abandonner. C'est le bon réflexe, à encourager fortement — l'honnêteté sur l'incompréhension reste son point fort constant depuis la première séance.
- Après un résumé conceptuel complet (le "filet de sécurité global", le rôle de chaque ligne expliqué), a répondu correctement et avec justification à une question de vérification transfert (le handler s'appliquerait-il aussi à un futur `LivreController` invalide, et pourquoi) — confirme que la compréhension a été récupérée, pas juste une façade de "c'est bon" pour clore la conversation.

### Enseignement méthodologique à en tirer pour les prochaines séances
**Sur les sujets architecturaux/abstraits (par opposition aux sujets "mécaniques" comme CRUD basique ou Git), poser explicitement le concept global et le "pourquoi" AVANT de commencer la décomposition en micro-étapes de code.** Sur les 2 premiers exercices (recherche par fourchette, validation), la décomposition immédiate a bien fonctionné parce que le "pourquoi" était intuitif/visible dès le départ (l'élève voyait tout de suite le problème concret). Sur `@ControllerAdvice`, le lien entre chaque petite étape technique et l'objectif global était moins évident dans l'instant, d'où le décrochage. Prévoir systématiquement une phase "vue d'ensemble" explicite avant la phase "construction pas à pas" sur ce type de sujet plus indirect.

## Niveau global (après 2 séances, 4 exercices)

- **Git : notions de base acquises, stables entre les séances** (pas retesté cette séance, mais aucun signe de perte).
- **Spring Boot : progression réelle et mesurable entre les séances.** Le transfert de patterns (`@ControllerAdvice`/`@RestControllerAdvice` déduit par analogie) est un vrai signe de montée en compétence, pas de mémorisation isolée. Les erreurs restantes sont cohérentes et ciblées : confusion champ/paramètre (portée des variables), inattention sur des choix déjà faits (relecture), syntaxe Java de base ponctuellement manquante (`Classe.class`). Aucune de ces lacunes n'est bloquante ; toutes progressent avec la pratique.
- **Point d'attention pédagogique central, désormais documenté sur 2 séances consécutives** : les sujets nécessitant de tenir une vue d'ensemble abstraite en tête pendant plusieurs étapes techniques (mapping Spring MVC composé, puis `ControllerAdvice`) sont ceux où l'élève décroche — pas par manque de capacité (il recolle les morceaux correctement une fois le concept explicité), mais parce que la charge cognitive de "suivre des instructions techniques sans le fil conducteur" dépasse un seuil. **Action concrète pour la suite : toujours amorcer un nouveau concept architectural par un résumé du "pourquoi" avant la première ligne de code, même sans qu'il le demande.**
- **Méthode de travail : son meilleur point reste sa capacité à dire honnêtement "je ne comprends pas" plutôt que de continuer à l'aveugle** — confirmé une deuxième fois, de façon plus franche encore que lors de la première séance. C'est la base sur laquelle construire toute la suite de la formation.

## Séance du 2026-09-26 (suite) — DTO et mapping (`ProduitRequestDTO` / `ProduitResponseDTO`)

Application directe de la leçon méthodologique tirée juste avant (poser le "pourquoi" avant le code). Résultat : **aucun décrochage cette fois**, séance fluide malgré un sujet tout aussi architectural que `ControllerAdvice`.

**Déroulé du "pourquoi" :** l'élève a lui-même testé et provoqué un vrai bug de sécurité concret (POST avec un `id` imposé par le client → `500 Internal Server Error`, `ObjectOptimisticLockingFailureException` de Hibernate qui traite l'entité comme une mise à jour au lieu d'une création) et en a tiré seul le bon diagnostic ("le controller fait trop confiance au client") avant qu'on introduise le concept de DTO comme solution. **Confirmation forte de la leçon de la séance précédente : quand le "pourquoi" est vécu concrètement avant le code, l'élève reste engagé et ne décroche pas, même sur un sujet abstrait.**

**Points forts observés :**
- A conçu correctement, sans aide, la distinction DTO d'entrée (sans `id`) / DTO de sortie (avec `id`) et l'a justifiée correctement.
- A proposé de lui-même de ne pas mettre de `setId()` sur le DTO de sortie, avec une justification défensive correcte (éviter la confusion/modification de l'identifiant) — bonne initiative de conception, pas demandée explicitement.
- A identifié et corrigé lui-même, après une question orientée, un mélange de responsabilités (avoir ajouté un champ `message` de statut dans un DTO censé représenter uniquement un produit) — bon raisonnement de conception quand challengé, même si l'erreur initiale montre une tendance à enrichir une classe au-delà de son rôle strict sans qu'on le lui demande.
- A correctement raisonné sur le déplacement des annotations de validation de l'entité vers le DTO d'entrée (suit la logique de `@Valid` plutôt que de la deviner au hasard).
- A justifié correctement le choix de placer le mapping DTO↔Entité dans le controller plutôt que le service, en re-mobilisant sa propre définition des couches donnée en tout début de formation (bon signe de rétention à long terme, pas seulement à court terme).

**Bug mineur persistant du même type que les séances précédentes** : a écrit deux fois `produit.setNom(dto.getNom())` en oubliant `setPrix(...)` — même famille d'erreur d'inattention/relecture que les séances 1 et 2 (copier-coller sans adapter, ou oubli d'une ligne). **Pattern maintenant confirmé sur 3 séances : l'élève ne relit pas systématiquement son code fraîchement écrit avant de le montrer.** C'est le point le plus constant et le plus actionnable à travailler explicitement (par exemple : lui demander systématiquement de relire lui-même avant de coller le code, plutôt que de le pointer à chaque fois).

## Niveau global (après 2 séances, 5 exercices)

- **Git : stable.**
- **Spring Boot : progression continue et confirmée.** L'élève construit maintenant des couches d'abstraction (DTO) avec une bonne intuition de conception (séparation des responsabilités, immutabilité défensive), au-delà de la simple exécution d'instructions. Sa vitesse de correction reste rapide, sa capacité à justifier ses choix avec les concepts appris plus tôt dans la formation est un signal de compréhension réelle et durable, pas de mémorisation à court terme.
- **Confirmation de la leçon méthodologique** : poser le "pourquoi" avant le code (idéalement via une démonstration concrète d'un problème réel, comme le crash provoqué ici) élimine le décrochage observé sur `ControllerAdvice`. **Cette méthode est à systématiser pour tout nouveau concept architectural.**
- **Point à travailler en priorité maintenant** : la relecture systématique de son propre code avant de le soumettre. C'est un pattern stable depuis 3 séances (comparaisons erronées, incohérences avec ses propres choix, copier-coller non adapté) — pas une question de compréhension mais de rigueur/habitude. Prochaine étape suggérée : lui demander explicitement "relis ta méthode ligne par ligne avant de me la montrer" en début d'exercice, pour voir si la consigne explicite suffit à casser le pattern, avant de continuer à le corriger systématiquement nous-mêmes.

### Séance du 2026-09-26 (suite 2) — Extraction spontanée d'un `ProduitMapper`

L'élève a posé de lui-même la question "y a-t-il un moyen plus rapide ?" en observant la répétition de son propre mapping DTO↔Entité — **initiative spontanée, pas suggérée**, bon signe d'esprit critique sur son propre code (dépasse la simple exécution de consignes).
- A retrouvé seul l'analogie avec `GlobalExceptionHandler` (centraliser plutôt que dupliquer) quand on l'a orienté dessus.
- A correctement raisonné que les méthodes de mapping n'ont pas besoin d'état → doivent être `static`, sans qu'on lui donne la réponse.
- Erreur mineure sans conséquence : typo `toRespondeDTO` (lettres interverties) au lieu de `toResponseDTO` — cohérent avec le pattern d'inattention déjà documenté, mais sans impact fonctionnel ici (juste esthétique/lisibilité).
- A câblé le mapper dans le controller sans erreur du premier coup cette fois — bon signe, la répétition du pattern (déjà fait 2 fois avec DTO/ControllerAdvice) commence à réduire le taux d'erreur de câblage.
- A été informé de l'existence de MapStruct (mapping automatique par génération de code) comme prochaine étape possible une fois le mapping manuel bien maîtrisé — non encore abordé en pratique, à garder en tête pour plus tard.

## Niveau global (après 2 séances, 5 exercices + 1 refactoring spontané)

- **Git : stable.**
- **Spring Boot : progression continue et confirmée.** L'élève construit maintenant des couches d'abstraction (DTO, Mapper) avec une bonne intuition de conception (séparation des responsabilités, immutabilité défensive, centralisation pour réduire la duplication), au-delà de la simple exécution d'instructions. Signal fort de cette séance : il a posé lui-même la question d'amélioration ("y a-t-il plus rapide ?") sans qu'on l'y invite — début d'un vrai réflexe de relecture critique de sa propre architecture, pas seulement de son code ligne à ligne (où le pattern d'inattention reste présent mais semble s'atténuer légèrement avec la répétition du même geste).
- **Confirmation de la leçon méthodologique** : poser le "pourquoi" avant le code élimine le décrochage. Cette méthode a été appliquée avec succès sur 2 sujets consécutifs (DTO, Mapper) après l'avoir identifiée sur `ControllerAdvice`.
- **Point à travailler toujours en priorité** : relecture systématique avant soumission — toujours présent mais fréquence d'erreurs de câblage en légère baisse sur les tâches répétées (le mapper a été branché sans bug, contrairement au premier mapping manuel).

### Séance du 2026-09-26 (suite 3) — Généralisation du Mapper à `getAll()`, `getOne()`, `update()`

Terminé en quasi-autonomie complète (guidage minimal, principalement des questions de vérification plutôt que de la décomposition pas-à-pas comme au début de la séance).

- A conçu seul `toResponseDTOList(List<Produit>)` en réutilisant sa propre méthode `toResponseDTO`, y compris le raisonnement sur la boucle — seule remarque : convention de nommage Java (variable `Produits` avec majuscule au lieu de `produits`), corrigée immédiatement.
- **Seule vraie erreur de la série** : a placé `@Valid` sur `@PathVariable Long id` au lieu de `@RequestBody ProduitRequestDTO p` dans `update()` — erreur de placement d'annotation plausible (les deux sont des paramètres de méthode), corrigée en une seule question ("est-ce que `id` a des annotations de validation dessus ?").
- A terminé `getOne()` sans aucune erreur, entièrement seul, sans étapes intermédiaires demandées.
- A testé l'ensemble du controller de bout en bout avant de déclarer la tâche terminée — réflexe de vérification maintenant bien ancré et systématique sur ce type de tâche.

**Résumé de la progression sur cette séance complète (DTO + Mapper)** : première moitié (DTO de base) encore guidée pas à pas avec plusieurs questions par ligne ; deuxième moitié (généralisation Mapper à 3 méthodes supplémentaires) réalisée avec un guidage réduit de moitié environ, une seule vraie erreur récurrente (placement d'annotation) au lieu de plusieurs par exercice comme en début de formation. **Vitesse d'autonomisation la plus nette observée jusqu'ici sur un pattern donné.**

## Niveau global (après 2 séances, 6 exercices + 1 refactoring spontané + 1 généralisation autonome)

- **Git : stable.**
- **Spring Boot : accélération de l'autonomie clairement mesurable.** Sur le pattern DTO+Mapper, l'élève est passé d'un guidage question-par-question à une quasi-autonomie en une seule séance, une fois le "pourquoi" posé au départ. C'est la preuve la plus concrète à ce jour que la méthode (poser le "pourquoi" avant le code, puis laisser une décomposition de plus en plus légère à mesure que le pattern se répète) fonctionne pour cet élève.
- **Erreur récurrente restante, mais de plus en plus rare et de plus en plus vite auto-corrigée** : placement d'annotations sur le mauvais paramètre/objet (vu sur `@RequestParam` vs `@PathVariable`, sur `@ExceptionHandler`, maintenant sur `@Valid`). C'est un point de vocabulaire/habitude Spring MVC spécifique, pas un problème de compréhension du concept — à surveiller mais plus la peine d'insister dessus à chaque fois, une question suffit maintenant à corriger.
- **Point suivant à surveiller** : est-ce que cette autonomie tient si on change de contexte (nouvelle entité, `LivreController`) plutôt que de rester sur `Produit` où le pattern a été répété 6 fois ? Bon test de transfert pour la prochaine séance.

## Séance du 2026-09-26 (suite 4) — Transfert complet à `LivreController`, en autonomie

Test de transfert : appliquer DTO + Mapper à une **nouvelle entité** (`Livre`), pas juste répéter sur `Produit`. **Résultat : transfert réussi, quasi sans guidage.**

**Ce que l'élève a produit seul, sans découpage en étapes de ma part :**
- `LivreRequestDTO` (sans `id`, annotations `@NotBlank`/`@Positive` correctement placées, y compris un champ `Boolean` optionnel `disponibiliter` avec un commentaire anticipant une valeur par défaut métier)
- `LivreResponseDTO` (avec `id`)
- `LivreMapper` (3 méthodes statiques, `toEntity`, conversion unité + liste, réutilisation interne correcte)
- `LivreController` entièrement réécrit avec DTO partout, **`@Valid` bien placé du premier coup sur les deux méthodes `POST`/`PUT`** (zéro erreur de ce type qui revenait sur les exercices précédents)
- Logique métier de valeur par défaut (`disponibiliter == null → true`) correctement implémentée dans le service dès la création — anticipée par l'élève lui-même, pas demandée.

**Un vrai bug métier trouvé et corrigé par le raisonnement, pas par l'erreur** : sur `modifier()`, un `PUT` sans `disponibiliter` aurait écrasé la valeur existante avec `null`. Repéré uniquement par une question ("que devient la valeur si le client ne l'envoie pas en update ?"), sans que l'élève l'ait vu spontanément — mais correction immédiate et juste (`if (!= null) { ... }`) dès que la question a été posée. Différence notable avec les bugs précédents : ce n'est pas une faute d'inattention/copier-coller, c'est un vrai angle mort de conception (cas non-nominal non anticipé), plus difficile à voir sans qu'on pointe le scénario précis.

**Incidents Git en cours de route, résolus par l'élève avec un minimum d'aide :**
- A committé un premier lot de fichiers **vides** (créés mais pas encore remplis) avant d'avoir terminé — a compris l'erreur seul après qu'on lui ait montré la différence entre `git status` (staged vs unstaged) et le contenu réel sur GitHub.
- A demandé et bien appliqué `git add .` depuis un sous-dossier (`src/`) comme stratégie pour éviter de toucher `.gitignore` par erreur — bonne généralisation d'un problème vécu 2 séances plus tôt.
- A oublié une fois `git add` avant `git commit` (erreur "nothing to commit" non comprise sur le coup) — corrigée après un rappel du cycle complet.
- A géré 4 cycles add/commit/push dans cette seule séance, sans erreur destructrice, avec un niveau de vérification (`git status`, `git diff --staged`) proche de ce qu'on attend d'un débutant sérieux plutôt que d'un pur débutant.

## Niveau global (après 2 séances, 6 exercices + 1 refactoring spontané + 1 transfert autonome complet)

- **Git : progression réelle au-delà des bases.** L'élève ne se contente plus d'exécuter les commandes une par une sur instruction : il choisit lui-même une stratégie (`git add .` scopé à un sous-dossier) pour éviter un problème déjà rencontré, et diagnostique des erreurs de séquence (`add` oublié, commit de fichiers vides) avec un minimum d'aide. Reste à voir : conflits de merge, toujours pas rencontrés en situation réelle.
- **Spring Boot : le transfert de compétence à une nouvelle entité est confirmé.** Ce n'était pas garanti — beaucoup d'élèves reproduisent un pattern sur le même objet sans être capables de le repartir de zéro sur un objet différent. Ici la généralisation à `Livre` a été quasi immédiate, avec zéro erreur de placement d'annotation (le point faible identifié il y a 2 séances) et une bonne anticipation métier spontanée (valeur par défaut).
- **Nouveau type d'erreur à noter, plus subtil que les précédents** : angle mort sur un cas non-nominal (update sans champ optionnel) — pas une faute d'inattention mais un manque de réflexe "quels sont tous les scénarios d'entrée possibles ?". C'est un pas au-dessus des bugs précédents (typos, mauvais placement) : ça demande de tester mentalement plusieurs chemins d'exécution, une compétence à développer explicitement dans les prochaines séances (poser systématiquement la question "que se passe-t-il si ce champ est absent/null/vide ?" avant de considérer une méthode terminée).
- **Méthode de travail : la relecture critique commence à s'installer au niveau architecture (mapper spontané), mais pas encore systématiquement au niveau des cas limites d'une méthode.** Prochaine étape logique.

### Prochaine séance : sujets en attente
- Introduire une pratique explicite de "test des cas limites" (valeurs null, vides, négatives, absentes) comme réflexe systématique avant de considérer un endpoint terminé — construire sur le bug `disponibiliter` de cette séance.
- Explorer MapStruct maintenant que le pattern manuel est bien consolidé sur 2 entités.
- Git avancé (conflits de merge, `pull`, `clone`) — toujours en attente, pourrait maintenant être abordé car les bases sont solides.
