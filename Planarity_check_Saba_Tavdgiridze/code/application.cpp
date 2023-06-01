#include <iostream>
#include <stdio.h>
#include <stdlib.h>

#include <graph/adjacency_list.hpp>
#include <graph/boyer_myrvold_planar_test.hpp>

int main(int argc, char ** argv) {

  using namespace boost;
  typedef adjacency_list<vecS, vecS, undirectedS, property<vertex_index_t, int>> graph;
  
  FILE * p_FILE = fopen(argv[1], "r");
  if (p_FILE) {
    int n, m;
    fscanf(p_FILE, "%d %d", &n, &m);

    graph GRAPH(n);
    for (int idx = 0; idx < m; idx++) {
      int a, b;
      fscanf(p_FILE, "%d %d", &a, &b);
      add_edge(a, b, GRAPH);
    }
    if (boyer_myrvold_planarity_test(GRAPH)) {
      printf("The graph is indeed planar!\n");
    } else {
      printf("The graph is not planar!\n");
    }
    fclose(p_FILE);
  }
  return 0;
}