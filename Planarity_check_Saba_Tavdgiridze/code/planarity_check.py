import subprocess
import re
import random


N = 2000
l_size = 10
r_size = 100

edge_choice_probability = 0.6

is_correct = True

for tries in range(N):
  with open("input_graph.txt", "w") as graph_file : 
    n = random.randint(l_size, r_size)
    
    graph = [[0 for __ in range(n)] for _ in range(n)]
    edge_count = 0

    for i in range(n) :
      for j in range(i+1, n) :
        if random.random() < edge_choice_probability :
          graph[i][j] = graph[j][i] = 1
          edge_count = edge_count + 1
    graph_file.write(str(n) + " " + str(edge_count) + "\n")
    for i in range(n):
      for j in range(i+1, n):
        if graph[i][j] == 1 :
          graph_file.write(str(i) + " " + str(j) + "\n")
  print("try : " + str(tries + 1) + "\n")
  output_java = subprocess.check_output("java Main input_graph.txt", shell=True).decode('utf-8')
  output_java = [ string for string in re.split("\n|\r", output_java) if string != ""]
  output_cpp = subprocess.check_output("application input_graph.txt", shell=True).decode('utf-8')


  what_says_my_app = False
  what_says_boost_implementation = False
  
  if (output_java == "The graph is planar!") :
    what_says_my_app = True
  if (output_cpp == "The graph is indeed planar!") :
    what_says_boost_implementation == True
  if what_says_my_app != what_says_boost_implementation:
    is_correct = False

  print("My implementation says: " + output_java[1] + "\n" + "Boost(C++) library implementation says: " + output_cpp)
  print("\n")

if is_correct:
  print("For all the cases, the results agreed!")
else:
  print("There was at least one case when the results differed!")