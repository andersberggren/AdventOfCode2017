from aoclib.filereader import get_file_as_list_of_string
from dec24.component import Component, ComponentChain

#############
# Functions #
#############
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
	all_components = [
		Component.create_from_string(s)
		for s in get_file_as_list_of_string("input24.txt")
	]
	chains = create_component_chains(all_components)
	strongest_chain = sorted(chains, key=lambda x: x.get_strength(), reverse=True)[0]
	print("Strongest chain:", strongest_chain)
	print("Strength:", strongest_chain.get_strength())
	max_length = max([x.get_length() for x in chains])
	longest_chains = [x for x in chains if x.get_length() == max_length]
	strongest_longest_chain = sorted(longest_chains, key=lambda x:x.get_strength(), reverse=True)[0]
	print("Strongest longest chain:", strongest_longest_chain)
	print("Strength:", strongest_longest_chain.get_strength())
