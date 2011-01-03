ALTER TABLE project ADD CONSTRAINT project_key1 UNIQUE(code, company);
ALTER TABLE project ADD CONSTRAINT project_key2 UNIQUE(name, company);

