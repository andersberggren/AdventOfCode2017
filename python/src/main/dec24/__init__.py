from aoclib.filereader import get_file_as_list_of_string
from dec24.component import Component, ComponentChain

#############
# Functions #
#############
def create_component_chain(chains_to_evaluate, finished_chains=None):
	i = 0
	if finished_chains is None:
		finished_chains = []
	while len(chains_to_evaluate) > 0:
		i += 1
		print("Iteration {i}. Evaluate={e} Finished={f}".format(
				i=i, e=len(chains_to_evaluate), f=len(finished_chains)))
		for chain in list(chains_to_evaluate):
			chains_to_evaluate.remove(chain)
			#print("Evaluate:", chain)
			successors = chain.get_successors()
			#for x in successors:
			#	print("Successor:", x)
			if len(successors) == 0:
				finished_chains.append(chain)
			else:
				chains_to_evaluate.update(successors)
	strongest_chain = sorted(finished_chains, key=lambda x: x.get_strength(), reverse=True)[0]
	print("Strongest chain:", strongest_chain)
	print("Strength:", strongest_chain.get_strength())

########
# Main #
########
if __name__ == "__main__":
	all_components = [
		Component.create_from_string(s)
		for s in get_file_as_list_of_string("input24.txt")
	]
	initial_chain = ComponentChain(all_components)
	create_component_chain({initial_chain})
