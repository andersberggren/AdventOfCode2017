class Component:
	def __init__(self, ports):
		self.ports = ports
	
	def to_simple_string(self):
		return "/".join([str(x) for x in self.ports])
	
	@staticmethod
	def create_from_string(s):
		ports = [int(x.strip()) for x in s.split("/")]
		if len(ports) != 2:
			raise ValueError("String didn't contain two ports:", s)
		return Component(ports)

class ComponentChain:
	def __init__(self, remaining_components, parent=None, component_to_attach=None):
		self.remaining_components = list(remaining_components)
		self.attached_components = []
		self.available_port = 0
		if parent is not None:
			self.attached_components = list(parent.attached_components)
			self.available_port = parent.available_port
		if component_to_attach is not None:
			self.attach_component(component_to_attach)
	
	def attach_component(self, component):
		for i in range(2):
			port = component.ports[i]
			other_port = component.ports[(i+1)%2]
			if port == self.available_port:
				self.attached_components.append(component)
				self.remaining_components.remove(component)
				self.available_port = other_port
				return
		raise ValueError("Component {c} can't connect to port {p}".format(
				c=component, p=self.available_port))
	
	def get_length(self):
		return len(self.attached_components)
	
	def get_strength(self):
		return sum(sum(c.ports) for c in self.attached_components)
	
	def get_successors(self):
		eligible_components_to_attach = [
			c for c in self.remaining_components
			if self.available_port in c.ports
		]
		return [
			ComponentChain(self.remaining_components, self, c)
			for c in eligible_components_to_attach
		]
	
	def __eq__(self, other):
		return set(self.attached_components) == set(other.attached_components) \
				and self.available_port == other.available_port
	
	def __hash__(self):
		value = 0
		for c in self.attached_components:
			value ^= c.__hash__()
		value ^= self.available_port
		return value
		
	def __repr__(self):
		chain = "-".join([c.to_simple_string() for c in self.attached_components])
		return "ComponentChain[length={l},strength={s},components={c}]".format(
			l=self.get_length(), s=self.get_strength(), c=chain)
