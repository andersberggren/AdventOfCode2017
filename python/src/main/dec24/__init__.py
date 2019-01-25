from aoclib.filereader import get_file_as_list_of_string
from dec24.component import Component, ComponentChain

#############
# Functions #
#############
def get_components_from_file(filename):
	return [Component.create_from_string(s) for s in get_file_as_list_of_string(filename)]
	
def create_component_chains(all_components):
	chains_to_evaluate = {ComponentChain(all_components)}
	finished_chains = []
	while len(chains_to_evaluate) > 0:
		for chain in list(chains_to_evaluate):
			chains_to_evaluate.remove(chain)
			successors = chain.get_successors()
			if len(successors) == 0:
				finished_chains.append(chain)
			else:
				chains_to_evaluate.update(successors)
	return finished_chains

########
# Main #
########
if __name__ == "__main__":
	all_components = get_components_from_file("input24.txt")
	chains = create_component_chains(all_components)
	
	# Part 1
	strongest_chain = sorted(chains, key=lambda x: x.get_strength(), reverse=True)[0]
	print("Strongest chain:", strongest_chain)
	
	# Part 2
	longest_chain = sorted(chains, key=lambda x: (-x.get_length(), -x.get_strength()))[0]
	print("Strongest longest chain:", longest_chain)
