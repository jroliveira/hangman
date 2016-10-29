# Hangman

[![CircleCI](https://circleci.com/gh/jroliveira/hangman.svg?style=svg)](https://circleci.com/gh/jroliveira/hangman)
[![Build Status](https://travis-ci.org/jroliveira/hangman.svg?branch=master)](https://travis-ci.org/jroliveira/hangman)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/24aac76d65dc480cbdfa93e0281f22fe)](https://www.codacy.com/app/jroliveira/hangman?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jroliveira/hangman&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/24aac76d65dc480cbdfa93e0281f22fe)](https://www.codacy.com/app/jroliveira/hangman?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jroliveira/hangman&amp;utm_campaign=Badge_Coverage)

Api para jogar o Jogo da Forca.

### Installing

``` bash
$ git clone https://github.com/jroliveira/hangman.git
```

### Running tests

``` bash
$ sbt 
> test
```

### How to use it

``` bash
$ sbt
> re-start
```

### How to deploy to Heroku

``` bash
$ sbt stage deployHeroku
```

### Contributions

1. Fork it
2. git checkout -b <branch-name>
3. git add --all && git commit -m "feature description"
4. git push origin <branch-name>
5. Create a pull request