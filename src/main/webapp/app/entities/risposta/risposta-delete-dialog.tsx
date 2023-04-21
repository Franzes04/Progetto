import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntity, deleteEntity } from './risposta.reducer';

export const RispostaDeleteDialog = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();
  const { id } = useParams<'id'>();

  const [loadModal, setLoadModal] = useState(false);

  useEffect(() => {
    dispatch(getEntity(id));
    setLoadModal(true);
  }, []);

  const rispostaEntity = useAppSelector(state => state.risposta.entity);
  const updateSuccess = useAppSelector(state => state.risposta.updateSuccess);

  const handleClose = () => {
    navigate('/risposta');
  };

  useEffect(() => {
    if (updateSuccess && loadModal) {
      handleClose();
      setLoadModal(false);
    }
  }, [updateSuccess]);

  const confirmDelete = () => {
    dispatch(deleteEntity(rispostaEntity.id));
  };

  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose} data-cy="rispostaDeleteDialogHeading">
        Conferma operazione di cancellazione
      </ModalHeader>
      <ModalBody id="progettoAlternanzaApp.risposta.delete.question">
        Sei sicuro di volere eliminare Risposta {rispostaEntity.id}?
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp; Annulla
        </Button>
        <Button id="jhi-confirm-delete-risposta" data-cy="entityConfirmDeleteButton" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp; Elimina
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export default RispostaDeleteDialog;
