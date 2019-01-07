import os
import re

from aoclib.filereader import get_file_as_list_of_string

#############
# Functions #
#############
def get_enhancement_rules_from_file(filename):
	enhancement_rules = {}
	lines = get_file_as_list_of_string(filename)
	for line in lines:
		match = re.match("([.#/]+) => ([.#/]+)", line)
		if match:
			pattern_from = string_to_image(match.group(1))
			pattern_to = match.group(2)
			for i in range(4):
				pattern_from = get_image_rotated_90_degrees(pattern_from)
				enhancement_rules[image_to_string(pattern_from)] = pattern_to
			pattern_from = get_image_flipped(pattern_from)
			for i in range(4):
				pattern_from = get_image_rotated_90_degrees(pattern_from)
				enhancement_rules[image_to_string(pattern_from)] = pattern_to
		else:
			raise RuntimeError("Unexpected line in input file: {}".format(line))
	return enhancement_rules

def get_initial_image():
	pattern = [".#.", "..#", "###"]
	location_to_symbol = {}
	for x in range(len(pattern[0])):
		for y in range(len(pattern)):
			location_to_symbol[(x,y)] = pattern[y][x]
	return location_to_symbol

def enhance(image, rules):
	image_size = max(location[0] for location in image) + 1
	old_pattern_size = 2 if image_size % 2 == 0 else 3
	new_pattern_size = 3 if old_pattern_size == 2 else 4
	new_image = {}
	#print("Image:")
	#print_image(image)
	print("Image size: {i}  Pattern size: {p}".format(i=image_size, p=old_pattern_size))
	for x in range(0, image_size, old_pattern_size):
		for y in range(0, image_size, old_pattern_size):
			old_pattern_str = image_to_string(image, (x,y), old_pattern_size)
			new_pattern = string_to_image(rules[old_pattern_str])
			location_in_new_image = (
				x//old_pattern_size*new_pattern_size,
				y//old_pattern_size*new_pattern_size
			)
			add_to_image(new_image, new_pattern, location_in_new_image)
	#print("New image:")
	#print_image(new_image)
	return new_image

def add_to_image(image, pattern_to_add, location):
	pattern_size = max(location[0] for location in pattern_to_add) + 1
	for x in range(pattern_size):
		for y in range(pattern_size):
			image[location[0]+x, location[1]+y] = pattern_to_add[(x,y)]

def image_to_string(image, location=(0,0), pattern_size=None):
	if pattern_size is None:
		pattern_size = max(coord[0] for coord in image) + 1
	string_list = []
	for y in range(pattern_size):
		s = ""
		for x in range(pattern_size):
			s += image[(location[0]+x, location[1]+y)]
		string_list.append(s)
	return "/".join(string_list)

def string_to_image(s):
	string_list = s.split("/")
	image_size = len(string_list)
	location_to_symbol = {}
	for x in range(image_size):
		for y in range(image_size):
			location_to_symbol[(x,y)] = string_list[y][x]
	return location_to_symbol

def get_image_rotated_90_degrees(image):
	image_size = max(location[0] for location in image) + 1
	new_image = {}
	for x in range(image_size):
		for y in range(image_size):
			new_image[(image_size-1-y,x)] = image[(x,y)]
	return new_image

def get_image_flipped(image):
	image_size = max(location[0] for location in image) + 1
	new_image = {}
	for x in range(image_size):
		for y in range(image_size):
			new_image[(x,image_size-1-y)] = image[(x,y)]
	return new_image

def print_image(image):
	image_size = max(location[0] for location in image) + 1
	for y in range(image_size):
		for x in range(image_size):
			print(image[(x,y)], end="")
		print()

########
# Main #
########
input_file = os.path.join(os.path.dirname(__file__), "input21.txt")
enhancement_rules = get_enhancement_rules_from_file(input_file)
image = get_initial_image()
for i in range(5):
	image = enhance(image, enhancement_rules)
print("Number of pixels that are on: {}".format(len([1 for x in image.values() if x == "#"])))
image = get_initial_image()
for i in range(18):
	image = enhance(image, enhancement_rules)
print("Number of pixels that are on: {}".format(len([1 for x in image.values() if x == "#"])))
