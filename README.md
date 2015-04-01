No-NonSense NoSQL
===============

###Presentation for [Greach](http://www.greachconf.com), [Gr8ConfEU](http://gr8conf.eu/#/talk/155), and [Gr8ConfUS](http://gr8conf.us)

####Madrid, Spain
#####11 April, 2015

####Copenhagen, Denmark
#####03 June, 2015

####Minneapolis, MN USA
#####July 2015

NoSQL continues to become more and more popular, but how do you pick which technology to use for your project? This session will cover the basics of NoSQL including several different types (key-value, graph, and document) as well as the advantages and disadvantages. The session will end with a review of Groovy plugins for NoSQL solutions like Redis and MongoDB.

Follow along at: http://jlstrater.github.io/No-Nonsense-NoSQL

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
* `grunt publish`
  * Publishes slides to GitHub pages
  * Uses git subtree merge to merge the contents of dist into the gh-pages branch
