The file is structured in the following way:
  It contains my implementation that is written in java (three files in total: Main.java Pair.java OrderedPair.java). I implemented the algorithm presented in the following lecture notes:
    http://monge.univ-mlv.fr/~colinde/cours/all-algo-embedded-graphs.pdf
  It also contains a C++ file (application.cpp) and a library folder graph (that is a part of C++ Boost library). The library implements the linear planarity checking algorithm. The library github repository:
    https://github.com/boostorg/boost
  Python script, that randomly constructs graphs and checks that both implementations give similar results. To check correctness of my implementation, run the following commands (incide "code" directory):
    "javac Main.java Pair.java OrderedPair.java"
    "g++ -I ../ -o application application.cpp"
    
    "python3 planarity_check.py" (this script, for now, generates N = 2000 graphs. Their number of edges is between 10 and 100. Of course, all parameters can be changed inside python script.) :
      The script gives answers to individual graphs by both implementations. In the end, it also checks if all the answers agreed between two implementations.
      
