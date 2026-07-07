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

After that, you can run:

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

## Workflow

- Note that all `devenv shell` commands are defined in `devenv.nix`.

### Data Collection

Example - Github action `Obtain latest hearings data`:

First, `.github/workflows/hearings-input.yml` runs:

```sh
devenv shell fetch-hearings
```

That calls the matching script in `input/`:

```
input/hearings/get_hearings.bb
```

The script fetches public data and writes it into:

```
data/hearings.json
```

Then the workflow runs:

```sh
devenv shell automated-git-push hearings
```

That commits and pushes any changed `data/` files back into the repo.

### Deployment

`.github/workflows/deploy.yml` deploys the site.

It runs when:

- manually triggered with `workflow_dispatch`
- pushed to main with changes under `docker/**` or `data/**` (i.e. new data is obtained)
