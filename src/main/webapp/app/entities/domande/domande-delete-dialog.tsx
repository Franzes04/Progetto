import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntity, deleteEntity } from './domande.reducer';

export const DomandeDeleteDialog = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();
  const { id } = useParams<'id'>();

  const [loadModal, setLoadModal] = useState(false);

  useEffect(() => {
    dispatch(getEntity(id));
    setLoadModal(true);
  }, []);

  const domandeEntity = useAppSelector(state => state.domande.entity);
  const updateSuccess = useAppSelector(state => state.domande.updateSuccess);

  const handleClose = () => {
    navigate('/domande');
  };

  useEffect(() => {
    if (updateSuccess && loadModal) {
      handleClose();
      setLoadModal(false);
    }
  }, [updateSuccess]);

  const confirmDelete = () => {
    dispatch(deleteEntity(domandeEntity.id));
  };

  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose} data-cy="domandeDeleteDialogHeading">
        Conferma operazione di cancellazione
      </ModalHeader>
      <ModalBody id="progettoAlternanzaApp.domande.delete.question">Sei sicuro di volere eliminare Domande {domandeEntity.id}?</ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp; Annulla
        </Button>
        <Button id="jhi-confirm-delete-domande" data-cy="entityConfirmDeleteButton" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp; Elimina
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export default DomandeDeleteDialog;
