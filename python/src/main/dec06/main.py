from aoclib.filereader import get_file_as_string

class Memory:
	def __init__(self, memory_banks):
		self.memory_banks = memory_banks
	
	def reallocate_until_repeated_state(self):
		""" Returns (number_of_reallocations, loop_size) """
		previous_configurations = []
		while True:
			configuration = tuple(self.memory_banks)
			if configuration not in previous_configurations:
				previous_configurations.append(configuration)
				self._reallocate_once()
			else:
				n_reallocations = len(previous_configurations)
				loop_size = n_reallocations - previous_configurations.index(configuration)
				return (n_reallocations, loop_size)
	
	def _reallocate_once(self):
		index_to_reallocate = self._get_index_to_reallocate()
		(n_blocks_to_all, n_blocks_remaining) = divmod(
				self.memory_banks[index_to_reallocate], len(self.memory_banks))
		self.memory_banks[index_to_reallocate] = 0
		for i in range(len(self.memory_banks)):
			self.memory_banks[i] += n_blocks_to_all
		for i in range(n_blocks_remaining):
			self.memory_banks[(index_to_reallocate+1+i)%len(self.memory_banks)] += 1
	
	def _get_index_to_reallocate(self):
		max_blocks = max(self.memory_banks)
		return next(i for i in range(len(self.memory_banks)) if self.memory_banks[i] == max_blocks)
	
	@staticmethod
	def create_from_file(filename):
		memory_banks = [int(x) for x in get_file_as_string(filename).split()]
		return Memory(memory_banks)

memory = Memory.create_from_file("input06.txt")
(n_reallocations, loop_size) = memory.reallocate_until_repeated_state()
print("Number of reallocations:", n_reallocations)
print("Loop size:", loop_size)
print(memory.memory_banks)
