import os
import re

from aoclib.filereader import get_file_as_list_of_string
from dec21.image import Image

#############
# Functions #
#############
def get_enhancement_rules_from_file(filename):
	"""
	Returns a dict of enhancement rules, where:
	- Key is pattern as a string (rows separated by "/")
	- Value is replacement pattern/image.
	"""
	enhancement_rules = {}
	for line in get_file_as_list_of_string(filename):
		match = re.match("([.#/]+) => ([.#/]+)", line)
		if match:
			pattern_from = Image.create_from_string(match.group(1))
			pattern_to = Image.create_from_string(match.group(2))
			for p in pattern_from.get_all_permutations_of_image():
				enhancement_rules[p.to_string()] = pattern_to
		else:
			raise RuntimeError("Unexpected line in input file: {}".format(line))
	return enhancement_rules

########
# Main #
########
if __name__ == "__main__":
	input_file = os.path.join(os.path.dirname(__file__), "input21.txt")
	enhancement_rules = get_enhancement_rules_from_file(input_file)
	image = Image.create_from_string(".#./..#/###")
	for i in [5, 18]:
		enhanced_image = image.get_enhanced_image(enhancement_rules, i)
		print("Number of pixels that are on after {i} iterations: {p}".format(
				i=i, p=len(enhanced_image.pixels)))
