package graphs.medium.topologicalSort;

import java.util.*;

// https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/description/
public class FindAllPossibleRecipesFromGivenSupplies_2115 {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        // Kahn's algo, topological sort
        // time O(V + E) = O(recipes.length + supplies.length + sum(ingredients[i].size))
        // space O(V + E)

        Map<String, List<String>> graph = new HashMap<>(); // Recipes and supplies are nodes in the graph. The ingredients list gives us the edges.
        Map<String, Integer> inDegree = new HashMap<>(); // {node, degree}
        Set<String> recipesSet = new HashSet<>(Arrays.asList(recipes)); // Needed so that later, while processing nodes during the topological sort, we only add recipes to the result.

        // init, add supply node to graph
        for(String sup : supplies) {
            graph.put(sup, new ArrayList<>());
            inDegree.put(sup, 0);
        }

        // init, add recipe nodes to graph
        for(String recipe : recipes) {
            graph.put(recipe, new ArrayList<>());
            inDegree.put(recipe, 0);
        }

        // build graph, add edges
        for(int i = 0; i < ingredients.size(); i++) {
            for(String ingredient : ingredients.get(i)) {
                graph.putIfAbsent(ingredient, new ArrayList<>());
                graph.get(ingredient).add(recipes[i]); // parent - ingredient, child - recipes[i]
                inDegree.put(recipes[i], inDegree.get(recipes[i]) + 1); // {child, degree that was before + 1}
            }
        }

        // topological sort
        Queue<String> sources = new LinkedList<>();
        for(String key : inDegree.keySet()) {
            if(inDegree.get(key) == 0)
                sources.add(key);
        }

        List<String> res = new ArrayList<>();
        while(!sources.isEmpty()) {
            String vertex = sources.poll();
            if(recipesSet.contains(vertex))
                res.add(vertex);

            for(String child : graph.get(vertex)) {
                inDegree.put(child, inDegree.get(child) - 1);
                if(inDegree.get(child) == 0)
                    sources.add(child);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        FindAllPossibleRecipesFromGivenSupplies_2115 find = new FindAllPossibleRecipesFromGivenSupplies_2115();
        List<List<String>> ingredients = new ArrayList<>();
        ingredients.add(Arrays.asList("yeast","flour"));
        ingredients.add(Arrays.asList("bread","meat"));
        ingredients.add(Arrays.asList("sandwich","meat","bread"));

        find.findAllRecipes(new String[]{"bread","sandwich","burger"}, ingredients, new String[]{"yeast","flour","meat"});
    }
}
