import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class Main {	
	public static void main(String args[]) {
		application_13(args);
	}
	
	public static void application_13(String args[]) {
		String fileName = args[0];
		try {
			System.out.println(fileName);
			Scanner reader = new Scanner(new File(fileName));

			// we assume that the graph is biconnected
			
			Map<Integer, Set<Integer>> graph = get_graph(reader);
			
			/**
			write_graph(graph);
			**/
			if (is_planar_general(graph)) {
				System.out.println("The graph is planar!");
			} else {
				System.out.println("The graph is non-planar!");
			}	
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void application_12(String args[]) {
		String fileName = args[0];
		try {
			System.out.println(fileName);
			Scanner reader = new Scanner(new File(fileName));

			// we assume that the graph is biconnected
			
			Map<Integer, Set<Integer>> graph = get_graph(reader);
			
			/**
			write_graph(graph);
			**/
			if (is_planar(graph)) {
				System.out.println("The graph is planar!");
			} else {
				System.out.println("The graph is non-planar!");
			}	
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void application_11() {
		Scanner reader = new Scanner(System.in);
		
		// we assume that the graph is biconnected
		
		Map<Integer, Set<Integer>> graph = get_graph(reader);
		write_graph(graph);
		
		List<Integer> circuit = new LinkedList<>();
		
		int circuit_length = reader.nextInt();
		for (int idx = 0; idx < circuit_length; idx++) {
			circuit.add(reader.nextInt());
		}
		
		Set<Pair> circuit_edges = new HashSet<Pair>();
		for (int idx = 0; idx < circuit.size(); idx++) {
			int next_idx = (idx + 1) % circuit.size();
			Pair edge = new Pair(circuit.get(idx), circuit.get(next_idx));
			circuit_edges.add(edge);
			System.out.printf("(%d, %d) ", edge.first, edge.second);
		}
		System.out.printf("\n");
		
		List<Map<Integer, Set<Integer>>> components = decompose_on_edges(graph, circuit_edges);
		
		for (Map<Integer, Set<Integer>> component : components) {
			write_graph(component);
		}
		
		Map<Integer, Set<Integer>> conflict_graph = find_conflict_graph(graph, components, circuit);
		
		write_graph(conflict_graph);
		
		reader.close();
	}
	
	public static void application_10() {
		Scanner reader = new Scanner(System.in);
		
		// we assume that the graph is connected
		
		Map<Integer, Set<Integer>> graph = get_graph(reader);
		write_graph(graph);
		
		List<Map<Integer, Set<Integer>>> biconnected_components = decompose_into_biconnected_components(graph);
		
		System.out.println("Biconnected components:");
		
		for (Map<Integer, Set<Integer>> biconnected_component : biconnected_components) {
			write_graph(biconnected_component);
		}
		
		reader.close();
	}
	
	public static void application_9() {
		Scanner reader = new Scanner(System.in);
		
		// we assume that the graph is connected
		
		Map<Integer, Set<Integer>> graph = get_graph(reader);
		write_graph(graph);
		
		int start_point, end_point;
		start_point = reader.nextInt();
		end_point = reader.nextInt();
		
		List<Integer> path = find_path(graph, start_point, end_point);
		
		for (Integer vertex : path) {
			System.out.printf("%d ", vertex);
		}
		System.out.printf("\n");
		
		reader.close();
	}
	
	public static void application_8() {
		Scanner reader = new Scanner(System.in);
		
		// we assume that the graph is connected
		Map<Integer, Set<Integer>> graph = get_graph(reader);
		write_graph(graph);
		
		Set<Pair> edge_set = get_edges(reader);
		
		
		List<Map<Integer, Set<Integer>>> graphs = decompose_on_edges(graph, edge_set);
		for (Map<Integer, Set<Integer>> component_graph : graphs) {
			write_graph(component_graph);
		}
		reader.close();
	}
	
	public static void application_7() {
		Scanner reader = new Scanner(System.in);
		
		
		// we assume that the graph is connected
		Map<Integer, Set<Integer>> graph = get_graph(reader);
		write_graph(graph);
		
		List<Integer> any_circuit = find_circuit(graph);
		if (any_circuit == null) {
			System.out.println("The graph is a tree!");
		} else {
			System.out.println("We find a circuit:");
			for (Integer vertex : any_circuit) {
				System.out.printf("%d ", vertex);
			}
			System.out.printf("\n");
		}
	}
	
	public static void application_6() {
		Scanner reader = new Scanner(System.in);
		
		Map<Integer, Set<Integer>> graph = get_graph(reader);
		write_graph(graph);
		
		if (is_biconnected(graph)) {
			System.out.println("The graph is biconnedted!");
		} else {
			System.out.println("The graph is not biconnected!");
		}
		
		System.out.printf("\n");
	}
	public static void application_5() {
		Scanner reader = new Scanner(System.in);
		
		Map<Integer, Set<Integer>> graph = get_graph(reader);
		write_graph(graph);
		
		Set<Integer> articulation_points = articulation_points(graph);
		
		System.out.println("These are the articulation points:");
		for (Integer point : articulation_points) {
			System.out.printf("%d ", point);
		}
		System.out.printf("\n");
	}
	
	public static void application_4() {
		Scanner reader = new Scanner(System.in);
		
		Map<Integer, Set<Integer>> graph = get_graph(reader);
		write_graph(graph);
		for (int vertex : graph.keySet()) {
			if (is_articulation_point(graph, vertex)) {
				System.out.printf("vertex %d is an articulation point for the graph\n", vertex);
			} else {
				System.out.printf("vertex %d is not an articulation point for the graph\n", vertex);
			}
		}
		reader.close();
	}
	
	public static void application_3() {
		Scanner reader = new Scanner(System.in);
		
		Map<Integer, Set<Integer>> graph = get_graph(reader);
		if (is_bipartite(graph)) {
			System.out.println("The graph is bipartite!");
		} else {
			System.out.println("The graph is not bipartite!");
		}
		
		reader.close();
	}
	
	public static void application_2() {
		List<Pair> vector = new ArrayList<Pair>();
		
		Scanner reader = new Scanner(System.in);
		Random random = new Random();
		
		int n = reader.nextInt();
		
		for (int idx = 1; idx <= n; idx++) {
			int a = random.nextInt(10);
			int b = random.nextInt(10);
			vector.add(new Pair(a, b));
		}
		
		for (Pair pair : vector) {
			System.out.printf("(%d, %d) ", pair.first, pair.second);
		}
		System.out.printf("\n");
		
		Collections.sort(vector, new Comparator<Pair>() {
			public int compare(Pair a, Pair b) {
				int result;
				if (a.first.equals(b.first)) {
					result = a.second.compareTo(b.second);
				} else {
					result = a.first.compareTo(b.first);
				}
				return result;
			}
		});
		
		for (Pair pair : vector) {
			System.out.printf("(%d, %d) ", pair.first, pair.second);
		}
		System.out.printf("\n");
		
		
		reader.close();
	}
	
	public static void application_1() {
		Scanner reader = new Scanner(System.in);
		Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
		
		graph = get_graph(reader);
		
		List<Map<Integer, Set<Integer>>> components = connected_components(graph);
		for (Map<Integer, Set<Integer>> component : components) {
			write_graph(component);
		}
		reader.close();
	}
	
	public static Map<Integer, Set<Integer>> get_graph(Scanner reader) {
		Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
		
		int n, m;
		n = reader.nextInt();
		m = reader.nextInt();
		for (int vertex = 1; vertex <= n; vertex++) {
			graph.put(vertex, new HashSet<Integer>());
		}
		for (int idx = 1; idx <= m; idx++) {
			int a,b;
			a = reader.nextInt();
			b = reader.nextInt();
			
			// because of the format of the input graph
			a++;
			b++;

			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		return graph;
	}
	public static Set<Pair> get_edges(Scanner reader) {
		int edges_count = reader.nextInt();
		Set<Pair> edges = new HashSet<Pair>();
		
		for (int edge_idx = 1; edge_idx <= edges_count; edge_idx++) {
			int a, b;
			a = reader.nextInt();
			b = reader.nextInt();
			
			edges.add(new Pair(a, b));
		}
		
		return edges;
	}
	
	public static List<Map<Integer, Set<Integer>>> connected_components(Map<Integer, Set<Integer>> graph) {
		int n_components = 0;
		List<Map<Integer, Set<Integer>>> components = new LinkedList<Map<Integer, Set<Integer>>>();
		
		Set<Integer> visited = new HashSet<Integer>();
		Map<Integer, Set<Integer>> component;
		
		for (Integer vertex : graph.keySet()) {
			if (!visited.contains(vertex)) {
				n_components++;
				
				component = new HashMap<Integer, Set<Integer>>();
			
				visited.add(vertex);
				component.put(vertex, new HashSet<Integer>());
				
				List<Integer> dfs_stack = new LinkedList<Integer>();
				dfs_stack.add(vertex);
				while(!dfs_stack.isEmpty()) {
					int current_vertex = dfs_stack.get(dfs_stack.size() - 1);
					dfs_stack.remove(dfs_stack.size() - 1);
					
					for (Integer neighbour : graph.get(current_vertex)) {
						component.get(current_vertex).add(neighbour);
						if (!visited.contains(neighbour)) {
							component.put(neighbour, new HashSet<Integer>());
							dfs_stack.add(neighbour);
							
							visited.add(neighbour);
						}
					}
				}
				components.add(component);
			}
		}
		return components;
	}
	
	
	public static boolean is_bipartite(Map<Integer, Set<Integer>> graph) {
		boolean is_bipartite = true;
		
		Map<Integer, Integer> color = new HashMap<Integer, Integer>();
		
		Integer first_vertex = graph.keySet().iterator().next();
		
		List<Integer> dfs_stack = new LinkedList<Integer>();
		
		dfs_stack.add(first_vertex);
		color.put(first_vertex, 0);
		
		while(!dfs_stack.isEmpty()) {
			Integer vertex = dfs_stack.get(dfs_stack.size() - 1);
			dfs_stack.remove(dfs_stack.size() - 1);
			
			for (Integer neighbour : graph.get(vertex)) {
				if (color.containsKey(neighbour)) {
					if (color.get(neighbour).equals(color.get(vertex))) {
						is_bipartite = false;
						return is_bipartite;
					}
				} else {
					color.put(neighbour, 1 - color.get(vertex));
					dfs_stack.add(neighbour);
				}
			}
		}
		return is_bipartite;
	}
	
	public static boolean is_articulation_point(Map<Integer, Set<Integer>> graph, int vertex) {
		// we assume that the graph is connected.
		
		int any_neigthbour = graph.get(vertex).iterator().next();
		
		Set<Integer> visited = new HashSet<Integer>();
		visited.add(any_neigthbour);
		
		List<Integer> dfs_stack = new LinkedList<Integer>();
		dfs_stack.add(any_neigthbour);
		
		while(!dfs_stack.isEmpty()) {
			int current_vertex = dfs_stack.get(dfs_stack.size() - 1);
			dfs_stack.remove(dfs_stack.size() - 1);
			
			for (int neighbour : graph.get(current_vertex)) {
				if (neighbour == vertex) {
					continue;
				}
				if (!visited.contains(neighbour)) {
					visited.add(neighbour);
					dfs_stack.add(neighbour);
				}
			}
		}
		
		visited.add(vertex);
		
		if (visited.containsAll(graph.keySet())) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public static Set<Integer> articulation_points(Map<Integer, Set<Integer>> graph) {
		// we assume that the graph is connected
		
		Set<Integer> articulation_points = new HashSet<Integer>();
		int root = graph.keySet().iterator().next();
		if (is_articulation_point(graph, root)) {
			articulation_points.add(root);
		}
		
		
		TreeMap<Integer, Integer> depth = new TreeMap<Integer, Integer>();
		depth.put(root, 0);
		
		Set<Integer> visited = new TreeSet<Integer>();
		Set<Integer> explored = new TreeSet<Integer>();
		
		visited.add(root);
		
		List<Integer> dfs_stack = new LinkedList<Integer>();
		dfs_stack.add(root);
		Map<Integer, Integer> low = new HashMap<Integer, Integer>();
		
		Map<Integer, Set<Integer>> taken_paths = new HashMap<>();
		taken_paths.put(root, new HashSet<Integer>());
		
		while(!dfs_stack.isEmpty()) {
			int current_vertex = dfs_stack.get(dfs_stack.size() - 1);
			if (explored.contains(current_vertex)) {
				dfs_stack.remove(dfs_stack.size() - 1);
				continue;
			}
			else {
				boolean is_explored = true;
				for (int neighbour : graph.get(current_vertex)) {
					if (!visited.contains(neighbour)) {
						is_explored = false;
						visited.add(neighbour);
						taken_paths.put(neighbour, new HashSet<Integer>());

						depth.put(neighbour, depth.get(current_vertex) + 1);
						
						taken_paths.get(current_vertex).add(neighbour);
						dfs_stack.add(neighbour);
						break;
					}
				}
				if (is_explored == true) {
					explored.add(current_vertex);
				}
			}
		}
		
		List<OrderedPair> depth_list = new LinkedList<OrderedPair>();
		for (int vertex : depth.keySet()) {
			depth_list.add(new OrderedPair(vertex, depth.get(vertex)));
		}
		Collections.sort(depth_list, new Comparator<OrderedPair>() {
			public int compare(OrderedPair a, OrderedPair b) {
				return b.second.compareTo(a.second);
			}
		});
		for (OrderedPair vertex_pair : depth_list) {
			int vertex = vertex_pair.first;
			low.put(vertex, depth.get(vertex));
			for (int neighbour : graph.get(vertex)) {
				if (depth.get(neighbour) <=  depth.get(vertex) - 1) {
					if (depth.get(neighbour) < depth.get(vertex) - 1) {
						low.put(vertex, Math.min(low.get(vertex), depth.get(neighbour)));
					}
				} else {
					if (low.get(neighbour) >= depth.get(vertex) && taken_paths.get(vertex).contains(neighbour)) {
						if (vertex != root) {
							articulation_points.add(vertex);
						}
					}
					low.put(vertex, Math.min(low.get(vertex), low.get(neighbour)));
				}
			}
		}
		
		return articulation_points;
	}
	
	public static boolean is_biconnected(Map<Integer, Set<Integer>> graph) {
		// we assume that the graph is connected
		return (articulation_points(graph).size() == 0);
	}
	
	public static List<Integer> find_circuit(Map<Integer, Set<Integer>> graph) {
		int root = graph.keySet().iterator().next();
		Set<Integer> visited = new HashSet<Integer>();
		Set<Integer> explored_dfs = new HashSet<Integer>();
		
		List<Integer> dfs_stack = new LinkedList<Integer>();
		
		visited.add(root);
		dfs_stack.add(root);
		
		while(!dfs_stack.isEmpty()) {
			int current_vertex = dfs_stack.get(dfs_stack.size() - 1);
			if (explored_dfs.contains(current_vertex)) {
				dfs_stack.remove(dfs_stack.size() - 1);
				continue;
			}
			boolean explored = true;
			
			for (int neighbour : graph.get(current_vertex)) {
				if (visited.contains(neighbour)) {
					if ( dfs_stack.size() >= 2 && dfs_stack.get(dfs_stack.size() - 2) != neighbour) {
						List<Integer> circuit = new LinkedList<Integer>();
						int start_idx = dfs_stack.indexOf(neighbour);
						for (int cycle_idx = start_idx; cycle_idx < dfs_stack.size(); cycle_idx++) {
							circuit.add(dfs_stack.get(cycle_idx));
						}
						return circuit;
					}
				} else {
					visited.add(neighbour);
					dfs_stack.add(neighbour);
					
					explored = false;
					break;
				}
			}
			if (explored == true) {
				explored_dfs.add(current_vertex);
			}
		}
		return null;
	}
	
	public static List<Map<Integer, Set<Integer>>> decompose_on_edges(Map<Integer, Set<Integer>> graph, Set<Pair> edges) {
		
		/**
		System.out.println("Staring decomposing the graph along circuit...\n");
		write_graph(graph);
		System.out.println("The circuit:");
		for (Pair edge : edges) {
			System.out.printf("(%d, %d) ", edge.first, edge.second);
		}
		System.out.printf("\n");
		**/
		
		List<Map<Integer, Set<Integer>>> components = new LinkedList<Map<Integer, Set<Integer>>>(); 
		int n_component = 0;
		
		
		Set<Integer> visited = new HashSet<Integer>();
		Map<Integer, Integer> component_group = new HashMap<Integer, Integer>();
		
		Set<Integer> edges_vertices = new HashSet<Integer>();
		for (Pair edge : edges) {
			edges_vertices.add(edge.first);
			edges_vertices.add(edge.second);
		}
		
		for (int vertex : graph.keySet()) {
			if (!visited.contains(vertex) && !edges_vertices.contains(vertex)) {
				n_component++;
				Map<Integer, Set<Integer>> component = new HashMap<Integer, Set<Integer>>();
				List<Integer> dfs_stack = new LinkedList<Integer>();
				
				dfs_stack.add(vertex);
				visited.add(vertex);
				
				
				component.put(vertex, new HashSet<Integer>());
				component_group.put(vertex, n_component);
				
				while(!dfs_stack.isEmpty()) {
					int current_vertex = dfs_stack.get(dfs_stack.size() - 1);
					dfs_stack.remove(dfs_stack.size() - 1);
					
					
					for (int neighbour : graph.get(current_vertex)) {
						if (visited.contains(neighbour) && component_group.get(neighbour) == n_component) {
							component.get(current_vertex).add(neighbour);
							component.get(neighbour).add(current_vertex);
						}
						if (!visited.contains(neighbour) || edges_vertices.contains(neighbour)) {
							if (!edges_vertices.contains(neighbour)) {
								visited.add(neighbour);
								dfs_stack.add(neighbour);
							}
							if (!component.keySet().contains(neighbour)) {
								component.put(neighbour, new HashSet<Integer>());
								component_group.put(neighbour, n_component);
							}							
							component.get(current_vertex).add(neighbour);
							component.get(neighbour).add(current_vertex);
						}
					}
				}
				if (component.keySet().size() > 1) {
					components.add(component);
				}
				
			}
		}
		
		for (int vertex : graph.keySet()) {
			if (edges_vertices.contains(vertex)) {
				for (int neighbour : graph.get(vertex)) {
					if (edges_vertices.contains(neighbour) && neighbour > vertex && !edges.contains(new Pair(vertex, neighbour))) {
						Map<Integer, Set<Integer>> component = new HashMap<Integer, Set<Integer>>();
						component.put(vertex, new HashSet<Integer>());
						component.get(vertex).add(neighbour);
						
						component.put(neighbour, new HashSet<Integer>());
						component.get(neighbour).add(vertex);
						components.add(component);
					}
				}
			}
		}
		/**
		System.out.println("The results:");
		for (Map<Integer, Set<Integer>> component : components) {
			write_graph(component);
		}
		**/
		return components;
		
	}
	
	public static List<Integer> find_path(Map<Integer, Set<Integer>> graph, Integer vertex_1, Integer vertex2) {
		Set<Integer> visited = new HashSet<Integer>();
		List<Integer> dfs_stack = new LinkedList<Integer>();
		
		Set<Integer> explored = new HashSet<Integer>();
		
		visited.add(vertex_1);
		dfs_stack.add(vertex_1);
		
		while(!dfs_stack.isEmpty()) {
			Integer current_vertex = dfs_stack.get(dfs_stack.size() - 1);
			if (explored.contains(current_vertex)) {
				dfs_stack.remove(dfs_stack.size() - 1);
				continue;
			}
			boolean is_explored = true;
			for (Integer neighbour : graph.get(current_vertex)) {
				if (visited.contains(neighbour)) {
					continue;
				} else {
					is_explored = false;
					visited.add(neighbour);
					dfs_stack.add(neighbour);
					if (neighbour == vertex2) {
						return dfs_stack;
					}
					break;
				}
			}
			if (is_explored == true) {
				explored.add(current_vertex);
			}
		}
		return null;
	}

	public static void biconnected_helper(int current_vertex, TreeMap<Integer, Integer> depth, Map<Integer, Integer> low, Map<Integer, Set<Integer>> taken_paths, Set<Integer> articulation_points, List<Map<Integer, Set<Integer>>> biconnected_components, int component_idx) {
		
		if (!articulation_points.contains(current_vertex)) {
			for (int neighbour : taken_paths.get(current_vertex)) {
				if (depth.get(neighbour) > depth.get(current_vertex)) {
					biconnected_components.get(component_idx).put(neighbour, new HashSet<Integer>());
					biconnected_helper(neighbour, depth, low, taken_paths, articulation_points, biconnected_components, component_idx);
				}
			}
		} else {
			if (depth.get(current_vertex) == 0) {
				biconnected_components.remove(biconnected_components.size() - 1);
				for (int neighbour : taken_paths.get(current_vertex)) {
					biconnected_components.add(new HashMap<Integer, Set<Integer>>());
					component_idx = biconnected_components.size() - 1;
					biconnected_components.get(component_idx).put(current_vertex, new HashSet<Integer>());
					biconnected_components.get(component_idx).put(neighbour, new HashSet<Integer>());
					biconnected_helper(neighbour, depth, low, taken_paths, articulation_points, biconnected_components, component_idx);
				}
				return;
			}
			
			
			int old_idx = component_idx;
			for (int neighbour: taken_paths.get(current_vertex)) {
				if (depth.get(neighbour) > depth.get(current_vertex)) {
					if (low.get(neighbour) >= depth.get(current_vertex)) {
						biconnected_components.add(new HashMap<Integer, Set<Integer>>());
						component_idx = biconnected_components.size() - 1;
						biconnected_components.get(component_idx).put(current_vertex, new HashSet<Integer>());
						biconnected_components.get(component_idx).put(neighbour, new HashSet<Integer>());
						biconnected_helper(neighbour, depth, low, taken_paths, articulation_points, biconnected_components, component_idx);
					} else {
						biconnected_components.get(old_idx).put(neighbour, new HashSet<Integer>());
						biconnected_helper(neighbour, depth, low, taken_paths, articulation_points, biconnected_components, old_idx);
					}
				}
			}
		}
	}
	
	public static List<Map<Integer, Set<Integer>>> decompose_into_biconnected_components(Map<Integer, Set<Integer>> graph) {
		// we assume that the graph is connected
		List<Map<Integer, Set<Integer>>> biconnected_components = new LinkedList<>();
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		Set<Integer> articulation_points = new HashSet<Integer>();
		int root = graph.keySet().iterator().next();
		if (is_articulation_point(graph, root)) {
			articulation_points.add(root);
		}
		
		
		TreeMap<Integer, Integer> depth = new TreeMap<Integer, Integer>();
		depth.put(root, 0);
		
		Set<Integer> visited = new TreeSet<Integer>();
		Set<Integer> explored = new TreeSet<Integer>();
		
		visited.add(root);
		
		List<Integer> dfs_stack = new LinkedList<Integer>();
		dfs_stack.add(root);
		
		Map<Integer, Integer> low = new HashMap<Integer, Integer>();
		
		Map<Integer, Set<Integer>> taken_paths = new HashMap<Integer, Set<Integer>>();
		taken_paths.put(root,  new HashSet<Integer>());
		
		while(!dfs_stack.isEmpty()) {
			
			// System.out.println("dfs stack: ");
			// System.out.println(dfs_stack);
			
			int current_vertex = dfs_stack.get(dfs_stack.size() - 1);
			if (explored.contains(current_vertex)) {
				dfs_stack.remove(dfs_stack.size() - 1);
				continue;
			}
			else {
				boolean is_explored = true;
				for (int neighbour : graph.get(current_vertex)) {
					if (!visited.contains(neighbour)) {
						is_explored = false;
						visited.add(neighbour);
						taken_paths.put(neighbour,  new HashSet<Integer>());
						depth.put(neighbour, depth.get(current_vertex) + 1);
						
						taken_paths.get(current_vertex).add(neighbour);
						taken_paths.get(neighbour).add(current_vertex);
						
						dfs_stack.add(neighbour);
						break;
					}
				}
				if (is_explored == true) {
					explored.add(current_vertex);
				}
			}
		}
		// System.out.println(root);
		// System.out.println(taken_paths);
		
		List<OrderedPair> depth_list = new LinkedList<OrderedPair>();
		for (int vertex : depth.keySet()) {
			depth_list.add(new OrderedPair(vertex, depth.get(vertex)));
		}
		Collections.sort(depth_list, new Comparator<OrderedPair>() {
			public int compare(OrderedPair a, OrderedPair b) {
				return b.second.compareTo(a.second);
			}
		});
		
		// System.out.println(depth_list);
		
		for (OrderedPair vertex_pair : depth_list) {
			int vertex = vertex_pair.first;
			low.put(vertex, depth.get(vertex));
			for (int neighbour : graph.get(vertex)) {
				if (depth.get(neighbour) <=  depth.get(vertex) - 1) {
					if (depth.get(neighbour)< depth.get(vertex) - 1) {
						//System.out.println(neighbour + " + " + vertex);
						low.put(vertex, Math.min(low.get(vertex), depth.get(neighbour)));
					}
				} else {
					// System.out.println(neighbour + " ++ " + vertex);
					if (taken_paths.get(vertex).contains(neighbour)) {
						if (low.get(neighbour) >= depth.get(vertex) && vertex != root) {
							articulation_points.add(vertex);
						}
						//System.out.println(neighbour + " + " + vertex);
						low.put(vertex, Math.min(low.get(vertex), low.get(neighbour)));
					}
				}
			}
		}
		//System.out.println(depth);
		//System.out.println(low);
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		int n_components = 0;
		biconnected_components.add(new HashMap<Integer, Set<Integer>>());
		biconnected_components.get(n_components).put(root, new HashSet<Integer>());
		
		//System.out.println(articulation_points);
		
		biconnected_helper(root, depth, low, taken_paths, articulation_points, biconnected_components, n_components);
		
		//System.out.println("***********************************");
		//System.out.println(biconnected_components);
		
		for (int component_idx = 0; component_idx <= biconnected_components.size() - 1; component_idx++) {
			for (int vertex : biconnected_components.get(component_idx).keySet()) {
				for (int neighbour : graph.get(vertex)) {
					if (biconnected_components.get(component_idx).keySet().contains(neighbour)) {
						biconnected_components.get(component_idx).get(vertex).add(neighbour);
						biconnected_components.get(component_idx).get(neighbour).add(vertex);
					}
				}
			}
		}
		
		//System.out.println(biconnected_components);
		//System.out.println("***********************************");
		////////////////////////////////////////////////////////////////////////////////////////////////
		return biconnected_components;
	}
	
	public static Map<Integer, Set<Integer>> find_conflict_graph(Map<Integer, Set<Integer>> graph, List<Map<Integer, Set<Integer>>> components, List<Integer> circuit) {
		Map<Integer, Set<Integer>> conflict_graph = new HashMap<Integer, Set<Integer>>();
		for (int idx = 0; idx < components.size(); idx++) {
			conflict_graph.put(idx, new HashSet<Integer>());
		}
		
		Map<Integer, Set<Integer>> circuit_conflict = new HashMap<Integer, Set<Integer>>();
		for (int idx = 0; idx < circuit.size(); idx++) {
			circuit_conflict.put(circuit.get(idx), new HashSet<Integer>());
		}
		
		Set<Integer> circuit_set = new HashSet<Integer>(circuit);
		
		Map<Integer, Set<Integer>> components_attach_vertices = new HashMap<Integer, Set<Integer>>();
		
		for (int component_idx = 0; component_idx < components.size(); component_idx++) {
			components_attach_vertices.put(component_idx, new HashSet<Integer>());
			for (int component_vertex : components.get(component_idx).keySet()) {
				if (circuit_set.contains(component_vertex)) {
					circuit_conflict.get(component_vertex).add(component_idx);
					components_attach_vertices.get(component_idx).add(component_vertex);
				}
			}
		}
		
		/**
		System.out.println(circuit_conflict);
		System.out.println(components_attach_vertices);
		System.out.println(circuit_set);
		**/
		
		for(int component_idx = 0; component_idx < components.size(); component_idx++) {
			List<Integer> component_attach = new LinkedList<Integer>();
			for (int component_vertex : components.get(component_idx).keySet()) {
				if (circuit_set.contains(component_vertex)) {
					if (!component_attach.contains(component_vertex)) {
						component_attach.add(component_vertex);
					}
				}
			}
			Collections.sort(component_attach, new Comparator<Integer>() {
				public int compare(Integer vertex_1, Integer vertex_2) {
					Integer idx_1 = circuit.indexOf(vertex_1);
					Integer idx_2 = circuit.indexOf(vertex_2);
					return idx_1.compareTo(idx_2);
				}
			});
			
			/**
			System.out.println(component_idx);
			System.out.println(component_attach);
			**/
			
			for (int other_component_idx = 0; other_component_idx < components.size(); other_component_idx++) {
				if (component_idx == other_component_idx) {
					continue;
				} else {
					
					boolean non_inteleaving = false;
					
					for (int idx = 0; idx < component_attach.size(); idx++) {
						int l_index = circuit.indexOf(component_attach.get(idx));
						int r_index = circuit.indexOf(component_attach.get((idx + 1)%component_attach.size()));
						
						/**
						System.out.printf("(%d, %d)\n", l_index, r_index);
						**/
						
						if (r_index < l_index) {
							r_index += circuit.size();
						}
						
						int attached_points_in_the_interval = 0;
								
						for (int checking_idx = l_index; checking_idx <= r_index; checking_idx++) {
							if (circuit_conflict.get(circuit.get(checking_idx % circuit.size())).contains(other_component_idx)) {
								attached_points_in_the_interval++;
							}
						}
						
						/**
						System.out.println(attached_points_in_the_interval);
						**/
						
						if (attached_points_in_the_interval == components_attach_vertices.get(other_component_idx).size()) {
							non_inteleaving = true;
						}
					}
						
					if (!non_inteleaving) {
						conflict_graph.get(component_idx).add(other_component_idx);
					}
				}
			}
			
		}
		return conflict_graph;
	}
	
	public static boolean is_planar(Map<Integer, Set<Integer>> graph, List<Integer> circuit) {
		// the graph is biconnected and we have already found a simple cycle in it
		
		Set<Pair> circuit_edges = new HashSet<Pair>();
		for (int idx = 0; idx < circuit.size(); idx++) {
			circuit_edges.add(new Pair(circuit.get(idx), circuit.get((idx + 1) % circuit.size())));
		}
		
		List<Map<Integer, Set<Integer>>> components = decompose_on_edges(graph, circuit_edges);
		
		
		/** 
		for (Map<Integer, Set<Integer>> component : components) {
			write_graph(component);
		}
		for (int idx = 0; idx < circuit.size(); idx++) {
			System.out.printf("%d ", circuit.get(idx));
		}
		System.out.printf("\n");
		
		System.out.println("--------------------------------------------------");
		System.out.println(circuit);
		for (Map<Integer, Set<Integer>> component : components) {
			write_graph(component);
		}
		
		System.out.println("**************************************************");
		**/
		if (components.size() == 0) {
			// the graph is a cycle and thus planar
			return true;
		} else {
			if (components.size() == 1) {
				Map<Integer, Set<Integer>> component = components.iterator().next();
				
				List<Integer> attachment_points = new LinkedList<Integer>();
				for (int vertex : component.keySet()) {
					Set<Integer> circuit_vertices = new HashSet<Integer>(circuit);
					if (circuit_vertices.contains(vertex)) {
						attachment_points.add(vertex);
					}
				}
				
				List<Integer> temp_circuit = new LinkedList<Integer>(circuit);
				Collections.sort(attachment_points, new Comparator<Integer>() {
					@Override
					public int compare(Integer vertex_1, Integer vertex_2) {
						Integer idx_1 = temp_circuit.indexOf(vertex_1);
						Integer idx_2 = temp_circuit.indexOf(vertex_2);
						return idx_1.compareTo(idx_2);
					}
				});
				
				Iterator<Integer> iterator_list = attachment_points.iterator();
				Integer vertex_1 = iterator_list.next();
				Integer vertex_2 = iterator_list.next();
				while (vertex_2 == vertex_1) {
					vertex_2 = iterator_list.next();
				}
				
				for (int attachment_vertex : attachment_points) {
					if (attachment_vertex == vertex_1 || attachment_vertex == vertex_2) {
						continue;
					}
					else {
						component.remove(attachment_vertex);
					}
				}
				
				
				
				for (int component_vertex : component.keySet()) {
					if (component_vertex == vertex_1 || component_vertex == vertex_2) {
						continue;
					}
					Set<Integer> temporary_neighbours = new HashSet<Integer>();
					
					for (int component_neighbour : component.get(component_vertex)) {
						if (component_neighbour != vertex_1 && component_neighbour != vertex_2 && attachment_points.contains(component_neighbour)) {
							continue;
						} else {
							temporary_neighbours.add(component_neighbour);
						}
					}
					component.put(component_vertex, temporary_neighbours);
				}
				
				
				List<Integer> path = find_path(component, vertex_1, vertex_2);
				
				if (path.size() == component.keySet().size()) {
					return true;
				} else {
					for (int idx_delete = circuit.indexOf(vertex_1); idx_delete < circuit.indexOf(vertex_2); idx_delete++) {
						circuit_edges.remove(new Pair(circuit.get(idx_delete), circuit.get(idx_delete + 1)));
					}
					for (int idx = 0; idx < path.size() - 1; idx ++) {
						Pair edge = new Pair(path.get(idx), path.get(idx + 1));
						circuit_edges.add(edge);
					}
					List<Integer> new_circuit = new LinkedList<Integer>();
					for (int idx = 0; idx < circuit.indexOf(vertex_1); idx++) {
						new_circuit.add(circuit.get(idx));
					}
					for (int vertex : path) {
						new_circuit.add(vertex);
					}
					for (int idx = circuit.indexOf(vertex_2) + 1; idx < circuit.size(); idx++) {
						new_circuit.add(circuit.get(idx));
					}
					circuit = new_circuit;
					circuit_edges = new HashSet<Pair>();
					for (int idx = 0; idx < circuit.size(); idx++) {
						circuit_edges.add(new Pair(circuit.get(idx), circuit.get((idx + 1) % circuit.size())));
					}
					components = decompose_on_edges(graph, circuit_edges);
				}
				
			}
		}
		//System.out.println(circuit);
		/**
		for (Map<Integer, Set<Integer>> component : components) {
			write_graph(component);
		}
		**/
		//System.out.println("--------------------------------------");
		
		// creating the conflict graph (we have at least pieces of the graph with respect to the circuit)
		
		Map<Integer, Set<Integer>> conflict_graph = find_conflict_graph(graph, components, circuit);
		
		/**
		
		System.out.println("The conflict graph:");
		write_graph(conflict_graph);
		
		**/
		
		if (!is_bipartite(conflict_graph)) {
			
			/**
			System.out.println("The conflict graph is not planar!");
			**/
			return false;
		}
		
		for (Map<Integer, Set<Integer>> component : components) {
			List<Integer> attachment_points = new LinkedList<Integer>();
			for (int vertex : component.keySet()) {
				if (circuit.contains(vertex)) {
					attachment_points.add(vertex);
				}
			}
			
			List<Integer> temp_circuit = new LinkedList<Integer>(circuit);
			Collections.sort(attachment_points, new Comparator<Integer>() {
				public int compare(Integer vertex_1, Integer vertex_2) {
					Integer idx_1 = temp_circuit.indexOf(vertex_1);
					Integer idx_2 = temp_circuit.indexOf(vertex_2);
					return idx_1.compareTo(idx_2);
				}
			});
			
			Iterator<Integer> iterator = attachment_points.iterator();
			Integer vertex_1 = iterator.next();
			Integer vertex_2 = iterator.next();
			
			////////////////////////////////////////////////////////////////////////////////////////////
			Map<Integer, Set<Integer>> component_temporary = new HashMap<Integer, Set<Integer>>();
			
			for (int component_vertex : component.keySet()) {
				if (attachment_points.contains(component_vertex)) {
					if (component_vertex == vertex_1 || component_vertex == vertex_2) {
						component_temporary.put(component_vertex, component.get(component_vertex));
					}
				} else {
					component_temporary.put(component_vertex, component.get(component_vertex));
				}
			}
			
			for (int component_vertex : component_temporary.keySet()) {
				if (component_vertex == vertex_1 || component_vertex == vertex_2) {
					continue;
				}
				Set<Integer> temporary_neighbours = new HashSet<Integer>();
				
				for (int component_neighbour : component_temporary.get(component_vertex)) {
					if (component_neighbour != vertex_1 && component_neighbour != vertex_2 && attachment_points.contains(component_neighbour)) {
						continue;
					} else {
						temporary_neighbours.add(component_neighbour);
					}
				}
				component_temporary.put(component_vertex, temporary_neighbours);
			}
			////////////////////////////////////////////////////////////////////////////////////////////


			List<Integer> path = find_path(component_temporary, vertex_1, vertex_2);
			int idx_1 = circuit.indexOf(vertex_1);
			int idx_2 = circuit.indexOf(vertex_2);
			
			
			
			List<Integer> component_circuit = new LinkedList<Integer>();
			
			for (int idx = 0; idx < idx_1; idx++) {
				component_circuit.add(circuit.get(idx));
			}
			for (int vertex : path) {
				component_circuit.add(vertex);
			}
			for (int idx = idx_2 + 1; idx < circuit.size(); idx++) {
				component_circuit.add(circuit.get(idx));
			}
			for (int vertex : circuit) {
				if (!component.containsKey(vertex)) {
					component.put(vertex, new HashSet<Integer>());
				}
			}
			for (int idx = 0; idx < circuit.size(); idx++) {
				component.get(circuit.get(idx)).add(circuit.get((idx+1)%circuit.size()));
				component.get(circuit.get((idx+1)%circuit.size())).add(circuit.get(idx));
			}
			
			if (!is_planar(component, component_circuit)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean is_planar_general(Map<Integer, Set<Integer>> graph) {
		List<Map<Integer, Set<Integer>>> connected_components = connected_components(graph);
		boolean is_planar = true;
		for (Map<Integer, Set<Integer>> connected_component : connected_components) {
			if (connected_component.keySet().size() == 1) {
				continue;
			}
			if (! is_planar_connected(connected_component)) {
				is_planar = false;
				break;
			}
		}

		return is_planar;
	}

	public static boolean is_planar_connected(Map<Integer, Set<Integer>> graph) {
		List<Map<Integer, Set<Integer>>> biconnected_components = decompose_into_biconnected_components(graph);
		boolean is_planar = true;
		for (Map<Integer, Set<Integer>> biconnected_component : biconnected_components) {
			if (biconnected_component.keySet().size() > 2) {
				if (!is_planar(biconnected_component)) {
					is_planar = false;
					break;
				}
			}
		}
		return is_planar;
	}

	public static boolean is_planar(Map<Integer, Set<Integer>> graph) {
		List<Integer> circuit = find_circuit(graph);
		return is_planar(graph, circuit);
	}	
	
	public static void write_graph(Map<Integer, Set<Integer>> graph) {
		System.out.printf("The graph contains %d vertices:\n", graph.size());
		for (Integer vertex : graph.keySet()) {
			System.out.printf("%d ", vertex);
		}
		System.out.printf("\n");
		System.out.println("These are the edges:");
		for (Integer vertex : graph.keySet()) {
			for (Integer neighbour : graph.get(vertex)) {
				if (neighbour > vertex) {
					System.out.printf("(%d - %d)\n", vertex, neighbour);
				}
			}
		}
		System.out.printf("\n");
	}
	
}
