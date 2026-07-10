# README by Choco Up

## Local Setup (Easy Version)

Check if Nix and Docker are installed.

```sh
nix --version
docker --version
```

Then, install `devenv`.

```sh
nix --extra-experimental-features nix-command --extra-experimental-features flakes profile add nixpkgs#devenv
```

Then check:

```sh
devenv --version
```

Then from the project root:

```sh
devenv shell
```

To build a local DB from the data in the `/data` folder, you can run:

```sh
devenv shell build-db
```

Start the local Datasette web server:

```sh
devenv shell dev-datasette
```

Then open:

```
http://localhost:8001
```

Normally, we use this for health check:

```
http://localhost:8001/-/versions.json
```

## GitHub Workflow

- Note that all `devenv shell` commands are defined in `devenv.nix`.

All data collection workflows, such as `Obtain latest hearings data` and `Obtain latest fc-judgments data`, will run regularly on `main` branch.

They collect data, write it into json file in `/data` folder, and push it to `main` branch.

After `Obtain latest hearings data` workflow is completed, the action of production deployment will run automatically:

```
.github/workflows/deploy-prd.yml
```

If you want to test dev environment, you need to run the action of dev deployment manually:

```
.github/workflows/deploy-dev.yml
```
