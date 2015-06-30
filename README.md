No-NonSense NoSQL
===============

NoSQL continues to become more and more popular, but how do you pick which technology to use for your project? This session will cover the basics of NoSQL including several different types (key-value, graph, and document) as well as the advantages and disadvantages. The session will end with a review of Groovy and Grails plugins for NoSQL solutions like Redis and MongoDB.

Follow along at: http://jlstrater.github.io/No-Nonsense-NoSQL

---

###Presented at [Greach](http://www.greachconf.com), [Iowa Code Camp](http://iowacodecamp.com), [Gr8ConfEU](http://gr8conf.eu/#/talk/155), and [Gr8ConfUS](http://gr8conf.us)

####Madrid, Spain
####Source Saved at [Release 1.0](https://github.com/jlstrater/No-Nonsense-NoSQL/releases/tag/1.0)
####Slides Saved at [https://jlstrater.github.io/No-Nonsense-NoSQL/Greach2015](https://jlstrater.github.io/No-Nonsense-NoSQL/Greach2015)
#####11 April 2015

####Cedar Rapids Iowa, USA
####Source Saved at [Release 2.0](https://github.com/jlstrater/No-Nonsense-NoSQL/releases/tag/2.0)
####Slides Saved at [https://jlstrater.github.io/No-Nonsense-NoSQL/IowaCodeCamp15](http://jlstrater.github.io/No-Nonsense-NoSQL/IowaCodeCamp15)
#####09 May 2015

####Copenhagen, Denmark
####Source Saved at [Release 3.0](https://github.com/jlstrater/No-Nonsense-NoSQL/releases/tag/3.0)
####Slides Saved at [https://jlstrater.github.io/No-Nonsense-NoSQL/Gr8ConfEU2015](http://jlstrater.github.io/No-Nonsense-NoSQL/Gr8ConfEU2015)
#####03 June 2015

####Minneapolis, MN USA
#####30 July 2015

---
####For the Examples

The code examples are setup on git branches.  

To see the code examples, use `git checkout $branchname` (after remembering to stash any changes).

We will start with the branch `ExampleStart` which will be the built-in H2 database.

Next is `MongoStart` After the code examples, skip to `MongoFinish` to see the solutions

For redis, start with `RedisStart` and skip to `RedisFinish` for the end.

----
##Slides

Based on the [OPI Reveal template](http://github.com/objectpartners/opi-reveal-template)

## Dependencies
* Node
* Bower
* grunt-cli

##Getting Started
* Run `npm install` to install node dependencies
* Run `bower install` to install client-side dependencies

##Grunt Commands
* `grunt assemble`
  * Minify/uglify the javascript source and css
  * Compiles jade
  * Stages everything in the dist folder
* `grunt run`
  * Starts a server running on port 8000
  * watches for changes on project files
  * When files change, the assemble task is re-run and
  * Livereload triggers browser update on assemble task completion

##Notes
Yes, I know that the print to pdf functionality is broken.  I'm working on it.