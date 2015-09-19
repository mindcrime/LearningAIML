# LearningAIML
Repo of the code I write as I learn AIML (Artificial Intelligence Markup Language).

learningAIML_1 - this dir contains a complete AIML bot that can respond to 3 possible inputs.  It's basically the "Hello World" of AIML bots.  Not interactive, the inputs are hardcoded and all it does is println() the output.

xmpp-aiml-bot - this dir contains a complete AIML bot that's wired up to XMPP so you can chat with it interactively.  Still basically just a "Hello World" example as far as AIML goes, and only responds to 3 or 4 possible inputs.  Beyond AIML, also implements "@ commands" and will return the current time in response to '@time'.

xmpp-aiml-osgi-bot - this dir contains the same bot as above, but the whole thing has been turned into an OSGI bundle that can be deployed into Apache Felix.  Note that the OSGI bundle, for now, is a crappy monolithic bundle with all the dependencies embedded.  It will be broken down into a more purely modular form eventually.  For now I just wanted Felix to mange the application lifecycle and to provide a container for running the code.  Later, as this thing starts to grow, being built on OSGI will provide a handy foundation for adding new funtionality in a modular fashion.

