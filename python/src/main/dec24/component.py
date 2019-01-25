class Component:
	def __init__(self, ports):
		self.ports = ports
	
	def __repr__(self):
		return "Component[{}]".format("/".join([str(x) for x in self.ports]))
	
	def to_simple_string(self):
		return "/".join([str(x) for x in self.ports])
	
	@staticmethod
	def create_from_string(s):
		ports = [int(x.strip()) for x in s.split("/")]
		if len(ports) != 2:
			raise ValueError("String didn't contain two ports:", s)
		return Component(ports)

class ComponentChain:
	def __init__(self, all_components, parent=None):
		self.all_components = all_components
		self.attached_components = []
		self.available_port = 0
		if parent is not None:
			self.attached_components = list(parent.attached_components)
			self.available_port = parent.available_port
	
	def add_component(self, component):
		if component.ports[0] == self.available_port:
			self.attached_components.append(component)
			self.available_port = component.ports[1]
		elif component.ports[1] == self.available_port:
			self.attached_components.append(component)
			self.available_port = component.ports[0]
		else:
			raise ValueError("Component {c} can't connect to port {p}".format(
				c=component, p=self.available_port))
	
	def get_length(self):
		return len(self.attached_components)
	
	def get_strength(self):
		return sum([sum(c.ports) for c in self.attached_components])
	
	def get_successors(self):
		successors = []
		eligible_components_to_attach = [
			c for c in self.all_components
			if c not in self.attached_components and self.available_port in c.ports
		]
		for component in eligible_components_to_attach:
			chain = ComponentChain(self.all_components, self)
			chain.add_component(component)
			successors.append(chain)
		return successors
	
	def __eq__(self, other):
		return set(self.attached_components) == set(other.attached_components) \
				and self.available_port == other.available_port
	
	def __hash__(self):
		value = 0
		for c in self.attached_components:
			value = value ^ c.__hash__()
		value = value ^ self.available_port
		return value
		
	def __repr__(self):
		chain = "-".join([c.to_simple_string() for c in self.attached_components])
		return "ComponentChain[{}]".format(chain)
