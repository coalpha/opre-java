NAME := opre
IN_DIR := src
OUT_DIR := bin

src_files = $(wildcard $(IN_DIR)/$(NAME)/*.java)
src_names = $(src_files:$(IN_DIR)/%.java=%)
artifacts = $(src_names:%=$(OUT_DIR)/%.class)

JAVAC_ARGS = -cp $(IN_DIR) -d $(OUT_DIR)
JAVA_ARGS  = -cp $(OUT_DIR)

test:
	@echo src_files = $(src_files)

build: $(artifacts)
	

jar: $(OUT_DIR)/$(NAME).jar
	

$(OUT_DIR)/$(NAME).jar: $(artifacts)
	jar cvfm $@ Manifest.txt -C $(OUT_DIR) .

$(OUT_DIR)/%.class: $(IN_DIR)/%.java
	@echo ----- MAK $< -----
	@javac $(JAVAC_ARGS) $<

clean:
	-rd /s /q "$(OUT_DIR)"

.PHONY: default build-all run~% noop~%
.PRECIOUS: %.class
