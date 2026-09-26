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

### Prochaine séance : sujets en attente
- DTO et mapping (séparer l'entité JPA de ce qu'on expose/reçoit via l'API) — bon sujet suivant naturel après validation/erreurs, avec un "pourquoi" à poser clairement dès le départ (éviter d'exposer l'entité brute).
- Git avancé (conflits de merge, `pull`, `clone`) toujours en attente si l'élève préfère y revenir avant.
