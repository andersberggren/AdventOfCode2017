class Image:
	def __init__(self, image_size, pixels=None):
		# Width and height of image.
		self.size = image_size
		# Set of (x,y) for pixels that are turned on.
		self.pixels = pixels
		if self.pixels is None:
			self.pixels = set()
	
	def get_all_permutations_of_image(self):
		permutations_of_image = []
		image = self
		for i in range(4):  # @UnusedVariable
			image = image._get_image_rotated_90_degrees()
			image_flipped = image._get_image_flipped()
			permutations_of_image.append(image)
			permutations_of_image.append(image_flipped)
		return permutations_of_image

	def _get_image_rotated_90_degrees(self):
		new_pixels = {
			(self.size-1-y, x)
			for x in range(self.size) for y in range(self.size)
			if (x,y) in self.pixels
		}
		return Image(self.size, new_pixels)
	
	def _get_image_flipped(self):
		new_pixels = {
			(x, self.size-1-y)
			for x in range(self.size) for y in range(self.size)
			if (x,y) in self.pixels
		}
		return Image(self.size, new_pixels)
	
	def get_enhanced_image(self, enhancement_rules, number_of_iterations):
		image = self
		for i in range(number_of_iterations):  # @UnusedVariable
			image = image._get_enhanced_image_one_iteration(enhancement_rules)
		return image
	
	def _get_enhanced_image_one_iteration(self, enhancement_rules):
		size_old_pattern = 2 if self.size % 2 == 0 else 3
		size_new_pattern = 3 if size_old_pattern == 2 else 4
		new_image = Image(self.size//size_old_pattern*size_new_pattern)
		print("Current image size: {ci}  Pattern size: {p}  New image size will be: {ni}".format(
				ci=self.size, p=size_old_pattern, ni=new_image.size))
		for x in range(0, self.size, size_old_pattern):
			for y in range(0, self.size, size_old_pattern):
				old_pattern_str = self.to_string((x,y), size_old_pattern)
				new_pattern = enhancement_rules[old_pattern_str]
				location_in_new_image = (
					x//size_old_pattern*size_new_pattern,
					y//size_old_pattern*size_new_pattern
				)
				new_image._add_pixels_from_image(new_pattern, location_in_new_image)
		return new_image
	
	def _add_pixels_from_image(self, image, anchor):
		for x in range(image.size):
			for y in range(image.size):
				if (x,y) in image.pixels:
					self.pixels.add((anchor[0]+x, anchor[1]+y))
	
	def to_string(self, anchor=(0,0), section_size=None):
		"""
		Returns a section of the image as a string. If "anchor" and "section_size"
		are omitted, the entire image will be returned.
		Arguments:
		  anchor        Top-left anchor (x,y) of the section to return.
		  section_size  Size of the section to return.
		"""
		if section_size is None:
			section_size = self.size
		rows = []
		for y in range(section_size):
			row = ""
			for x in range(section_size):
				if (anchor[0]+x, anchor[1]+y) in self.pixels:
					row += "#"
				else:
					row += "."
			rows.append(row)
		return "/".join(rows)
	
	@staticmethod
	def create_from_string(s):
		"""
		Returns an Image with size and pixels accoring to the string "s".
		Arguments:
		  s  The image string. "#" is a pixel that is on. Rows separated with "/".
		"""
		string_list = s.split("/")
		image_size = len(string_list)
		if not all (len(x) == image_size for x in string_list):
			raise ValueError("Height is {s}, but not all rows are {s} wide".format(s=image_size))
		pixels = {
			(x,y) for x in range(image_size) for y in range(image_size)
			if string_list[y][x] == "#"
		}
		return Image(image_size, pixels)
